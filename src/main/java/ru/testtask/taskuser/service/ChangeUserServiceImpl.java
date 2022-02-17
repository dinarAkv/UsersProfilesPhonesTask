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

    @Override
    public long createUser(CreateRequest createRequest) {
        Users users = new Users();
        for (String phoneVal : createRequest.getPhones()) {
            users.addPhone(phoneVal);
        }
        users.setName(createRequest.getName());
        users.setEmail(createRequest.getEmail());
        users.setAge(createRequest.getAge());
        users.setCash(createRequest.getCash());
        return usersRepository.save(users).getId();
    }
}
