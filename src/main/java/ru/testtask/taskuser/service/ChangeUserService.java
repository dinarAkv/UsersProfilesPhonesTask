package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Validated
public interface ChangeUserService {

    long createUser(@Valid CreateRequest createRequest);

    @Builder
    @Value
    class CreateRequest {
        @NotNull
        String name;
        int age;
        @NotNull
        String email;
        @NotEmpty
        String cash;
        @NotEmpty
        Collection<String> phones;
    }


}
