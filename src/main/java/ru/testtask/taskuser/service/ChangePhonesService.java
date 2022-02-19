package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ChangePhonesService {

    void addPhone(@Valid AddPhoneRequest addPhoneRequest);
    void changePhone(@Valid ChangePhoneRequest changePhoneRequest);
    void deletePhone(@Valid DeletePhoneRequest deletePhoneRequest);

    @Builder
    @Value
    class AddPhoneRequest {
        long userId;
        @NotNull
        String phoneValue;
    }

    @Builder
    @Value
    class DeletePhoneRequest {
        long userId;
        @NotNull
        String phoneValue;
    }

    @Builder
    @Value
    class ChangePhoneRequest {
        long userId;
        @NotNull
        String oldPhoneValue;
        @NotNull
        String newPhoneValue;
    }
}
