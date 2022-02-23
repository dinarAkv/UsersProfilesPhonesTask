package ru.testtask.taskuser.service.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.config.security.jwt.RoleCode;

import java.util.Set;

public class UserSecurityServiceImplTest extends AbstractLocalTest {

    @Autowired
    UserSecurityService userSecurityService;

    @Test
    void auth_test() {
        String login = "testLogin";
        String password = "testPassword";
        Set<RoleCode> roles = Set.of(RoleCode.ROLE_USER);
        registerUserAccount(login, password, roles);

        UserSecurityService.AuthResponse response = userSecurityService.auth(UserSecurityService.AuthRequest.builder()
                .login(login)
                .password(password)
                .build());
        Assertions.assertThat(response.getToken()).isNotEmpty();
    }
}
