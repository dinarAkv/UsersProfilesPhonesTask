package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ChangeProfileService {

    void changeProfile(@Valid ChangeProfileRequest changeProfileRequest);

    @Builder
    @Value
    class ChangeProfileRequest {
        long userId;
        @NotNull
        String cash;
    }
}
