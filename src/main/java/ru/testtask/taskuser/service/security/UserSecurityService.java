package ru.testtask.taskuser.service.security;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
public interface UserSecurityService {

    AuthResponse auth(@Valid AuthRequest request);

    @Builder
    @Value
    public class AuthRequest {
        @NotEmpty
        String login;
        @NotEmpty
        String password;
    }

    @Builder
    @Value
    class AuthResponse {
        @NotEmpty
        String token;
    }
}
