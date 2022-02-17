package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;

public interface ChangePhonesService {

    void addPhone(AddPhoneRequest addPhoneRequest);
    void changePhone(ChangePhoneRequest changePhoneRequest);
    void deletePhone(DeletePhoneRequest deletePhoneRequest);

    @Builder
    @Value
    class AddPhoneRequest {
        Long userId;
        String phoneValue;
    }

    @Builder
    @Value
    class DeletePhoneRequest {
        Long userId;
        String phoneValue;
    }

    @Builder
    @Value
    class ChangePhoneRequest {
        Long userId;
        String oldPhoneValue;
        String newPhoneValue;
    }
}
