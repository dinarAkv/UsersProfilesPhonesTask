package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.PhonesRepository;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ChangePhonesServiceImpl implements ChangePhonesService {

    final UsersRepository usersRepository;
    final PhonesRepository phonesRepository;
    final CommonUtilService commonUtilService;

    @Override
    public void addPhone(AddPhoneRequest addPhoneRequest) {
        log.debug("Add phone {} request for user {}", addPhoneRequest.getPhoneValue(), addPhoneRequest.getUserId());
        Users user = commonUtilService.getUserById(addPhoneRequest.getUserId());
        user.addPhone(addPhoneRequest.getPhoneValue());
        usersRepository.save(user);
        log.debug("Phone {} added", addPhoneRequest.getPhoneValue());
    }

    @Override
    public void changePhone(ChangePhoneRequest changePhoneRequest) {
        log.debug("Change phone {} to {} request for user {}",
                changePhoneRequest.getOldPhoneValue(), changePhoneRequest.getNewPhoneValue(),
                changePhoneRequest.getUserId());
        String oldPhoneValue = changePhoneRequest.getOldPhoneValue();
        Long userId = changePhoneRequest.getUserId();
        Phones phone = phonesRepository.findByPhoneAndUserId(oldPhoneValue,
                userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Phone with id %s and userId %s not found", oldPhoneValue, userId)));
        phone.setPhone(changePhoneRequest.getNewPhoneValue());
        Phones changedPhone = phonesRepository.save(phone);
        log.debug("Phone changed to {}", changedPhone.getPhone());
    }

    @Override
    public void deletePhone(DeletePhoneRequest deletePhoneRequest) {
        log.debug("Delete phone {} request for user {}", deletePhoneRequest.getPhoneValue(),
            deletePhoneRequest.getUserId());
        Users user = commonUtilService.getUserById(deletePhoneRequest.getUserId());
        user.deletePhone(deletePhoneRequest.getPhoneValue());
        usersRepository.save(user);
        log.debug("Phone {} removed", deletePhoneRequest.getPhoneValue());
    }
}
