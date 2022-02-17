package ru.testtask.taskuser.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.model.Profiles;
import ru.testtask.taskuser.model.Users;

import java.math.BigDecimal;

public class ChangeProfileServiceImplTest extends AbstractLocalTest {

    @Autowired
    ChangeProfileService changeProfileService;

    @Test
    void changeProfile_test() {
        Users user = createUser();
        String newCashVal = String.valueOf(5_000_000.41);
        changeProfileService.changeProfile(ChangeProfileService.ChangeProfileRequest.builder()
                .userId(user.getId())
                .cash(newCashVal)
                .build());
        flushAndClearSession();

        user = usersRepository.findById(user.getId()).get();
        Assertions.assertThat(user)
                .extracting(Users::getProfiles)
                .extracting(Profiles::getCash)
                .isEqualTo(new BigDecimal(newCashVal));
    }
}
