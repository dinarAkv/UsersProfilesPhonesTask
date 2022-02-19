package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Validated
public interface ChangeUserService {

    UserDataResponse createUser(@Valid CreateUserRequest createUserRequest);
    UserDataResponse changeUser(@Valid ChangeUserRequest changeUserRequest);

    @Builder
    @Value
    class CreateUserRequest {
        @NotNull
        String name;
        Integer age;
        @NotNull
        String email;
        @NotEmpty
        String cash;
        Set<String> phones;
    }

    @Builder
    @Value
    class ChangeUserRequest {
        @NotNull
        Long userId;
        CreateUserRequest userData;
    }

    @Builder
    @Value
    class UserDataResponse {
        @NotNull
        Long id;
        @NotNull
        String name;
        int age;
        @NotNull
        String email;
        @NotEmpty
        String cash;
        Set<String> phones;
    }
}
