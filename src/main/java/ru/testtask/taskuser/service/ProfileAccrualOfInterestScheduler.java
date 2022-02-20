package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.testtask.taskuser.dao.repositories.ProfilesRepository;
import ru.testtask.taskuser.model.Profiles;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileAccrualOfInterestScheduler {

    final ProfilesRepository profilesRepository;

    @Value("${ru.testtask.taskuser.profile.max.income.factor}")
    private String maxIncomeFactor;
    @Value("${ru.testtask.taskuser.profile.interest.rate.factor}")
    private String interestRateFactor;

    @Scheduled(fixedRate = 20, timeUnit = TimeUnit.SECONDS)
    public void scheduleProfileInterest() {
        log.info("Begin handling profiles for interest accrual");
        Collection<Profiles> profiles = profilesRepository
                .getAvailableProfileForAccrualInterest(new BigDecimal(maxIncomeFactor));
        if (profiles.isEmpty()) {
            log.info("No profiles to accrual interest");
            return;
        }

        BigDecimal interestRateFactorBd = new BigDecimal(this.interestRateFactor);
        for (Profiles profile : profiles) {
            log.info("Handling profile of user {} ", profile.getUser().getId());
            profile.setCash(profile.getCash().multiply(interestRateFactorBd));

            if ((profile.getInitCash().multiply(new BigDecimal(maxIncomeFactor))
                    .compareTo(profile.getCash()) < 0)) {
                log.info("Profile cash of user {} reach maximum value ", profile.getUser().getId());
                profile.setCash(profile.getInitCash().multiply(new BigDecimal(maxIncomeFactor)));
            }
        }
        profilesRepository.saveAll(profiles);
        log.info("End handling profile for interest accrual");
    }
}
