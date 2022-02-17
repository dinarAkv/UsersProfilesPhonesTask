package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testtask.taskuser.service.ChangeUserService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                    path = "/api/users")
@RequiredArgsConstructor
public class UsersController {

    final ChangeUserService changeUserService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody ChangeUserService.CreateRequest createRequest) {
        changeUserService.createUser(createRequest);
    }


}
