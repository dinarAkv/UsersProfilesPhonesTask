package ru.testtask.taskuser.service;

import lombok.Builder;
import lombok.Value;

public interface ChangeProfileService {

    void changeProfile(ChangeProfileRequest changeProfileRequest);

    @Builder
    @Value
    class ChangeProfileRequest {
        Long userId;
        String cash;
    }
}
