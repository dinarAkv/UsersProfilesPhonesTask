package ru.testtask.taskuser.service;


import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.model.Profiles;
import ru.testtask.taskuser.model.Users;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class ProfileAccrualOfInterestSchedulerTest extends AbstractLocalTest {

    @Autowired
    ProfileAccrualOfInterestScheduler profileAccrualOfInterestScheduler;

    @Value("${ru.testtask.taskuser.profile.max.income.factor}")
    String maxIncomeFactor;
    @Value("${ru.testtask.taskuser.profile.interest.rate.factor}")
    private String interestRateFactor;

    @Test
    void scheduleProfileInterest_test() {
        Users user = createUser();
        profileAccrualOfInterestScheduler.scheduleProfileInterest();
        flushAndClearSession();

        user = usersRepository.findById(user.getId()).get();
        Profiles profile = user.getProfiles();
        Assertions.assertThat(profile.getCash().doubleValue() / profile.getInitCash().doubleValue())
                .isCloseTo(Double.valueOf(interestRateFactor), Percentage.withPercentage(0.1));

        IntStream.range(0, 100).forEach(i -> profileAccrualOfInterestScheduler.scheduleProfileInterest());
        flushAndClearSession();

        Assertions.assertThat(profile.getCash())
                .isLessThanOrEqualTo(profile.getInitCash().multiply(new BigDecimal(maxIncomeFactor)));
    }
}
