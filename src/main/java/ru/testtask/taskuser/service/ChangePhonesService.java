package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
public interface ChangePhonesService {

    void addPhone(@Valid AddPhoneRequest addPhoneRequest);
    void changePhone(@Valid ChangePhoneRequest changePhoneRequest);
    void deletePhone(@Valid DeletePhoneRequest deletePhoneRequest);

    @Builder
    @Value
    class AddPhoneRequest {
        long userId;
        @NotEmpty
        String phoneValue;
    }

    @Builder
    @Value
    class DeletePhoneRequest {
        long userId;
        @NotEmpty
        String phoneValue;
    }

    @Builder
    @Value
    class ChangePhoneRequest {
        long userId;
        @NotEmpty
        String oldPhoneValue;
        @NotEmpty
        String newPhoneValue;
    }
}
