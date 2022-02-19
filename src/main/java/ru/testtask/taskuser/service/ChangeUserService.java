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

    long createUser(@Valid CreateUserRequest createUserRequest);
    void changeUser(@Valid ChangeUserRequest changeUserRequest);

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
}
