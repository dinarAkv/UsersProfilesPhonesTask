package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testtask.taskuser.service.security.UserSecurityService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        path = "/api/security")
@RequiredArgsConstructor
public class SecurityController {

    final UserSecurityService userSecurityService;

    @PostMapping("/auth")
    public UserSecurityService.AuthResponse auth(@RequestBody UserSecurityService.AuthRequest request) {
        return userSecurityService.auth(request);
    }
}
