package ru.testtask.taskuser.service.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.config.security.jwt.RoleCode;
import ru.testtask.taskuser.config.security.jwt.TaskUserDetails;

import java.util.Set;

public class TaskUserDetailsServiceTest extends AbstractLocalTest {

    @Autowired
    TaskUserDetailsService userDetailsService;

    @Test
    void loadUserByUsername_test() {
        String login = "testLogin";
        String password = "testPassword";
        Set<RoleCode> roles = Set.of(RoleCode.ROLE_USER);
        registerUserAccount(login, password, roles);

        TaskUserDetails userDetails = userDetailsService.loadUserByUsername(login);
        Assertions.assertThat(userDetails.getUsername()).isEqualTo(login);
        Assertions.assertThat(passwordEncoder.matches(password, userDetails.getPassword())).isTrue();
        Assertions.assertThat(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)).containsExactlyInAnyOrder(RoleCode.ROLE_USER.name());
    }
}
