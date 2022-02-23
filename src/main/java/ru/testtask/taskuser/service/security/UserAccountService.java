package ru.testtask.taskuser.service.security;

import ru.testtask.taskuser.model.UsersAccount;

public interface UserAccountService {

    UsersAccount findUserByLogin(String login);

    UsersAccount findByLoginAndPassword(String user, String password);
}
