package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testtask.taskuser.service.security.AdminSecurityService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        path = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    final AdminSecurityService adminSecurityService;

    @PostMapping("/register")
    public AdminSecurityService.RegisterResponse registerAccount(AdminSecurityService.RegisterRequest request) {
        return adminSecurityService.registerAccount(request);
    }
}
