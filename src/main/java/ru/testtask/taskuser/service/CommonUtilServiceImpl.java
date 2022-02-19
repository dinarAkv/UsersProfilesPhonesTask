package ru.testtask.taskuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.excpetions.EntityNotFoundException;
import ru.testtask.taskuser.model.Users;

@Service
public class CommonUtilServiceImpl implements CommonUtilService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users getUserById(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(userId, Users.class));
        return user;
    }
}
