package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Validated
public interface GetUserDataService {

    UserDataResponse getUserById(Long userId);
    List<UserDataResponse> filterUsers(@Valid FilteredRequest request);

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

    @Builder
    @Value
    class FilteredRequest {
        Integer age;
        String phone;
        String nameLike;
        String email;
        @NotNull
        Integer page;
        @NotNull
        Integer pageSize;
    }
}
