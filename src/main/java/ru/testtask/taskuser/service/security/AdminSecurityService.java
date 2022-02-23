package ru.testtask.taskuser.service.security;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;
import ru.testtask.taskuser.config.security.jwt.RoleCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Validated
public interface AdminSecurityService {

    RegisterResponse registerAccount(@Valid RegisterRequest request);

    @Builder
    @Value
    class RegisterRequest {
        @NotEmpty
        String login;
        @NotEmpty
        String password;
        @NotEmpty
        Set<RoleCode> roles;
    }

    @Builder
    @Value
    class RegisterResponse {
        String login;
    }
}
