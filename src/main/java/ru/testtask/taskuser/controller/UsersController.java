package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.testtask.taskuser.service.ChangeUserService;
import ru.testtask.taskuser.service.GetUserDataService;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                    path = "/api/users")
@RequiredArgsConstructor
public class UsersController {

    final ChangeUserService changeUserService;
    final GetUserDataService getUserDataService;

    @GetMapping("/getUserById/{userId}")
    public GetUserDataService.UserDataResponse getUserById(@PathVariable Long userId) {
        return getUserDataService.getUserById(userId);
    }

    @GetMapping("/getUsersByFilter")
    public List<GetUserDataService.UserDataResponse> getUsersByFilter(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String nameLike,
            @RequestParam(required = false) String email,
            @RequestParam Integer page, @RequestParam Integer pageSize) {
        return getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .age(age).phone(phone).nameLike(nameLike).email(email)
                        .page(page)
                        .pageSize(pageSize)
                        .build());
    }

    @PostMapping("/createUser")
    public ChangeUserService.UserDataResponse createUser(
            @RequestBody ChangeUserService.CreateUserRequest createUserRequest) {
        return changeUserService.createUser(createUserRequest);
    }

    @PostMapping("/changeUser")
    public ChangeUserService.UserDataResponse changeUser(
            @RequestBody ChangeUserService.ChangeUserRequest changeUserRequest) {
        return changeUserService.changeUser(changeUserRequest);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        changeUserService.deleteUser(userId);
    }
}
