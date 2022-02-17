package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testtask.taskuser.service.ChangeProfileService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        path = "/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    final ChangeProfileService changeProfileService;

    @PostMapping("/changeProfile")
    public void changeProfile(ChangeProfileService.ChangeProfileRequest request) {
        changeProfileService.changeProfile(request);
    }

}
