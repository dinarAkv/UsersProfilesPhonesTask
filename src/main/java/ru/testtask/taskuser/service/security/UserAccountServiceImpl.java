package ru.testtask.taskuser.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.testtask.taskuser.dao.repositories.UsersAccountRepository;
import ru.testtask.taskuser.excpetions.IncorrectLoginOrPasswordException;
import ru.testtask.taskuser.excpetions.UserAccountNotFoundException;
import ru.testtask.taskuser.model.UsersAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    final UsersAccountRepository usersAccountRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UsersAccount findUserByLogin(String login) {
        return usersAccountRepository.findByLogin(login)
                .orElseThrow(() -> new UserAccountNotFoundException(login));
    }

    @Override
    public UsersAccount findByLoginAndPassword(String user, String password) {
        UsersAccount userAccount = findUserByLogin(user);
        if (passwordEncoder.matches(password, userAccount.getPassword())) {
            return userAccount;
        }
        throw new IncorrectLoginOrPasswordException();
    }
}
