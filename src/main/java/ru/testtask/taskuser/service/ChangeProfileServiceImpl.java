package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Users;

@Transactional
@RequiredArgsConstructor
@Service
public class ChangeProfileServiceImpl implements ChangeProfileService {

    final UsersRepository usersRepository;
    final CommonUtilService commonUtilService;

    @Override
    public void changeProfile(ChangeProfileRequest changeProfileRequest) {
        Users user = commonUtilService.getUserById(changeProfileRequest.getUserId());
        user.setCash(changeProfileRequest.getCash());
        usersRepository.save(user);
    }
}
