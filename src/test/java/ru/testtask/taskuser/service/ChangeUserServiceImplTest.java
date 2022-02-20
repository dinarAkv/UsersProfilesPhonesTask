package ru.testtask.taskuser.service;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class ChangeUserServiceImplTest extends AbstractLocalTest {

    @Autowired
    ChangeUserService changeUserService;

    @Test
    void createUser_test() {
        ChangeUserService.UserDataResponse createdUser = changeUserService.createUser(
                ChangeUserService.CreateUserRequest.builder()
                .name("Ivan")
                .email("IvanTY@mail.com")
                .age(34)
                .cash("123300.45")
                .phones(Set.of("83452342233", "83452342231"))
                .build());
        Assertions.assertThat(createdUser.getId()).isGreaterThan(0L);
    }

    @Test
    void changeUser_test() {
        Users user = createUser();
        ChangeUserService.ChangeUserRequest request = ChangeUserService.ChangeUserRequest.builder()
                .name("Stepan2")
                .age(40)
                .cash("300000")
                .email("stepanIM2@mail.com")
                .phones(Set.of("89211112233", "892111122331", "892111122332"))
                .userId(user.getId())
                .build();
        changeUserService.changeUser(request);
        flushAndClearSession();

        user = usersRepository.findById(user.getId()).get();
        Assertions.assertThat(user.getPhones())
                .extracting(Phones::getPhone)
                .containsExactlyInAnyOrderElementsOf(request.getPhones());
        Assertions.assertThat(user)
                .extracting(Users::getEmail, Users::getAge, Users::getName)
                .containsExactly(request.getEmail(), request.getAge(), request.getName());
        Assertions.assertThat(user.getProfiles().getCash())
                .isCloseTo(new BigDecimal(request.getCash()), Percentage.withPercentage(0.001));
    }

    @Test
    void deleteUser_test() {
        Users user = createUser();

        changeUserService.deleteUser(user.getId());
        flushAndClearSession();

        Optional<Users> userOpt = usersRepository.findById(user.getId());
        Assertions.assertThat(userOpt.isPresent()).isFalse();
    }
}
