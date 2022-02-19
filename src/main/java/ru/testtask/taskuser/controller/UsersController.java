package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.testtask.taskuser.service.ChangeUserService;
import ru.testtask.taskuser.service.GetUserDataService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                    path = "/api/users")
@RequiredArgsConstructor
public class UsersController {

    final ChangeUserService changeUserService;
    final GetUserDataService getUserDataService;

    @GetMapping("/getUserById/{userId}")
    public void createUser(@PathVariable Long userId) {
        getUserDataService.getUserById(userId);
    }

    @GetMapping("/getUsersByFilter")
    public void createUser(@RequestParam(required = false) Integer age,
                           @RequestParam(required = false) String phone,
                           @RequestParam(required = false) String nameLike,
                           @RequestParam(required = false) String email,
                           @RequestParam Integer page, @RequestParam Integer pageSize) {
        getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .age(age).phone(phone).nameLike(nameLike).email(email)
                        .page(page)
                        .pageSize(pageSize)
                        .build());
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody ChangeUserService.CreateUserRequest createUserRequest) {
        changeUserService.createUser(createUserRequest);
    }

    @PostMapping("/changeUser")
    public void changeUser(@RequestBody ChangeUserService.CreateUserRequest createUserRequest) {
        changeUserService.createUser(createUserRequest);
    }


}
