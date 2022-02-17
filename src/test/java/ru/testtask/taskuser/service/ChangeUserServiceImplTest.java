package ru.testtask.taskuser.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;

import java.util.Set;

public class ChangeUserServiceImplTest extends AbstractLocalTest {

    @Autowired
    ChangeUserService changeUserService;

    @Test
    void createUser_test() {
        long userId = changeUserService.createUser(ChangeUserService.CreateRequest
                .builder()
                .name("Ivan")
                .email("IvanTY@mail.com")
                .age(34)
                .cash("123300.45")
                .phones(Set.of("83452342233", "83452342231"))
                .build());
        Assertions.assertThat(userId).isGreaterThan(0L);
    }

}
