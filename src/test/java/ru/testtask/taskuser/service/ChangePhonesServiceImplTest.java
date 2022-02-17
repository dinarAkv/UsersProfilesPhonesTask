package ru.testtask.taskuser.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.dao.repositories.PhonesRepository;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

public class ChangePhonesServiceImplTest extends AbstractLocalTest {

    @Autowired
    ChangePhonesService changePhonesService;
    @Autowired
    PhonesRepository phonesRepository;

    @Test
    void addPhone_test() {
        Users user = createUser();
        ChangePhonesService.AddPhoneRequest request = ChangePhonesService.AddPhoneRequest.builder()
                .phoneValue("89211112237")
                .userId(user.getId())
                .build();
        changePhonesService.addPhone(request);
        flushAndClearSession();

        Phones phone = phonesRepository.findByPhoneAndUserId(request.getPhoneValue(),
                user.getId()).orElse(null);
        Assertions.assertThat(phone).isNotNull();
    }

    @Test
    void changePhone_test() {
        Users user = createUser();

        String existingPhone = user.getPhones().iterator().next().getPhone();
        String newPhone = existingPhone + "5";
        changePhonesService.changePhone(ChangePhonesService.ChangePhoneRequest.builder()
                .oldPhoneValue(existingPhone)
                .newPhoneValue(newPhone)
                .userId(user.getId())
                .build());
        flushAndClearSession();

        Phones phone = phonesRepository.findByPhoneAndUserId(newPhone, user.getId()).orElse(null);
        Assertions.assertThat(phone).isNotNull();
    }

    @Test
    void deletePhone_test() {
        Users user = createUser();

        Phones phoneToDelete = user.getPhones().iterator().next();
        changePhonesService.deletePhone(ChangePhonesService.DeletePhoneRequest.builder()
                .phoneValue(phoneToDelete.getPhone())
                .userId(user.getId())
                .build());
        flushAndClearSession();

        Phones phoneResult = phonesRepository.findByPhoneAndUserId(phoneToDelete.getPhone(),
                user.getId()).orElse(null);
        Assertions.assertThat(phoneResult).isNull();
    }
}
