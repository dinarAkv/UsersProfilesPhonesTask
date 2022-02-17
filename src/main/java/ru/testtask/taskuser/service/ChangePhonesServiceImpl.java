package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.PhonesRepository;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.excpetions.EntityNotFoundException;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

@Transactional
@RequiredArgsConstructor
@Service
public class ChangePhonesServiceImpl implements ChangePhonesService {

    final UsersRepository usersRepository;
    final PhonesRepository phonesRepository;

    @Override
    public void addPhone(AddPhoneRequest addPhoneRequest) {
        Users user = getUserById(addPhoneRequest.getUserId());
        user.addPhone(addPhoneRequest.getPhoneValue());
        usersRepository.save(user);
    }

    @Override
    public void changePhone(ChangePhoneRequest changePhoneRequest) {
        String oldPhoneValue = changePhoneRequest.getOldPhoneValue();
        Long userId = changePhoneRequest.getUserId();
        Phones phone = phonesRepository.findByPhoneAndUserId(oldPhoneValue,
                userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Phone with id %s and userId %s not found", oldPhoneValue, userId)));
        phone.setPhone(changePhoneRequest.getNewPhoneValue());
        phonesRepository.save(phone);
    }

    @Override
    public void deletePhone(DeletePhoneRequest deletePhoneRequest) {
        Users user = getUserById(deletePhoneRequest.getUserId());
        user.deletePhone(deletePhoneRequest.getPhoneValue());
        usersRepository.save(user);
    }

    private Users getUserById(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(userId, Users.class));
        return user;
    }
}