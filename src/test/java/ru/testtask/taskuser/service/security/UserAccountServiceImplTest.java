package ru.testtask.taskuser.service.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.config.security.jwt.RoleCode;
import ru.testtask.taskuser.model.UsersAccount;
import ru.testtask.taskuser.model.UsersRole;

import java.util.Set;

public class UserAccountServiceImplTest extends AbstractLocalTest {

    @Autowired
    UserAccountService userAccountService;

    @Test
    void findUserByLogin_test() {
        String login = "testLogin";
        String password = "testPassword";
        registerUserAccount(login, password, Set.of(RoleCode.ROLE_USER));
        flushAndClearSession();

        UsersAccount userAccount = userAccountService.findUserByLogin(login);
        Assertions.assertThat(userAccount).isNotNull()
            .extracting(UsersAccount::getLogin)
                .isEqualTo(login);
        Assertions.assertThat(userAccount.getRoles()
                .stream().map(UsersRole::getRoleCode)).containsSequence(RoleCode.ROLE_USER);
        Assertions.assertThat(passwordEncoder.matches(password, userAccount.getPassword()));
    }

    @Test
    void findByLoginAndPassword_test() {
        String login = "testLogin";
        String password = "testPassword";
        registerUserAccount(login, password, Set.of(RoleCode.ROLE_USER));
        flushAndClearSession();

        UsersAccount userAccount = userAccountService.findByLoginAndPassword(login, password);
        Assertions.assertThat(userAccount).isNotNull()
                .extracting(UsersAccount::getLogin)
                .isEqualTo(login);
        Assertions.assertThat(userAccount.getRoles()
                .stream().map(UsersRole::getRoleCode)).containsSequence(RoleCode.ROLE_USER);
        Assertions.assertThat(passwordEncoder.matches(password, userAccount.getPassword()));
    }
}
