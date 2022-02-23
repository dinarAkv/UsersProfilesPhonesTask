package ru.testtask.taskuser.service.security;

import lombok.Builder;
import lombok.Value;
import ru.testtask.taskuser.config.security.jwt.RoleCode;

import java.util.Set;

public interface AdminSecurityService {

    RegisterResponse registerAccount(RegisterRequest request);

    @Builder
    @Value
    class RegisterRequest {
        String login;
        String password;
        Set<RoleCode> roles;
    }

    @Builder
    @Value
    class RegisterResponse {
        String login;
    }
}
