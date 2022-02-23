package ru.testtask.taskuser.service.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.config.security.jwt.RoleCode;
import ru.testtask.taskuser.model.UsersAccount;

import java.util.Set;

public class AdminSecurityServiceImplTest extends AbstractLocalTest {

    @Autowired
    AdminSecurityService adminSecurityService;

    @Test
    void registerAccount_test() {
        AdminSecurityService.RegisterRequest request = AdminSecurityService.RegisterRequest.builder()
                .login("testLogin")
                .password("testPassword")
                .roles(Set.of(RoleCode.ROLE_USER))
                .build();
        AdminSecurityService.RegisterResponse response =
                adminSecurityService.registerAccount(request);
        Assertions.assertThat(response.getLogin()).isEqualTo(request.getLogin());

        UsersAccount usersAccount = usersAccountRepository.findByLogin(request.getLogin()).orElse(null);
        Assertions.assertThat(usersAccount).isNotNull();

    }
}
