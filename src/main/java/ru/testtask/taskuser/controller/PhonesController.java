package ru.testtask.taskuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.testtask.taskuser.service.ChangePhonesService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        path = "/api/phones")
@RequiredArgsConstructor
public class PhonesController {

    final ChangePhonesService changePhonesService;

    @PostMapping("/addPhone")
    public void addPhone(@RequestBody ChangePhonesService.AddPhoneRequest request) {
        changePhonesService.addPhone(request);
    }

    @PostMapping("/changePhone")
    public void changePhone(@RequestBody ChangePhonesService.ChangePhoneRequest request) {
        changePhonesService.changePhone(request);
    }

    @DeleteMapping("/deletePhone/{userId}/{phoneToDelete}")
    public void deletePhone(@PathVariable Long userId, @PathVariable String phoneToDelete) {
        changePhonesService.deletePhone(ChangePhonesService.DeletePhoneRequest.builder()
                .phoneValue(phoneToDelete)
                .userId(userId)
                .build());
    }
}
