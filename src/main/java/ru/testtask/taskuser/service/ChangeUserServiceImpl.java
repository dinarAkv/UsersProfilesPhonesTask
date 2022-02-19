package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Users;

@Transactional
@RequiredArgsConstructor
@Service
public class ChangeUserServiceImpl implements ChangeUserService {

    final UsersRepository usersRepository;
    final CommonUtilService commonUtilService;

    @Override
    public long createUser(CreateUserRequest createUserRequest) {
        Users users = new Users();
        for (String phoneVal : createUserRequest.getPhones()) {
            users.addPhone(phoneVal);
        }
        users.setName(createUserRequest.getName());
        users.setEmail(createUserRequest.getEmail());
        users.setAge(createUserRequest.getAge());
        users.setCash(createUserRequest.getCash());
        return usersRepository.save(users).getId();
    }

    @Override
    public void changeUser(ChangeUserRequest changeUserRequest) {
        Users user = commonUtilService.getUserById(changeUserRequest.getUserId());
        user.setName(changeUserRequest.getUserData().getName());
        user.setAge(changeUserRequest.getUserData().getAge());
        user.setEmail(changeUserRequest.getUserData().getEmail());
        user.setCash(changeUserRequest.getUserData().getCash());
        user.replacePhones(changeUserRequest.getUserData().getPhones());
    }
}
