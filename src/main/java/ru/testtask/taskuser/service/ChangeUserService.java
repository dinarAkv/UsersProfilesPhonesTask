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
    void deleteUser(Long userId);

    @Builder
    @Value
    class CreateUserRequest {
        @NotEmpty
        String name;
        @NotNull
        Integer age;
        @NotEmpty
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
        String name;
        Integer age;
        String email;
        String cash;
        Set<String> phones;
    }

    @Builder
    @Value
    class UserDataResponse {
        @NotNull
        Long id;
        @NotEmpty
        String name;
        int age;
        @NotEmpty
        String email;
        @NotEmpty
        String cash;
        Set<String> phones;
    }
}
