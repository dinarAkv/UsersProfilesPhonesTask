package ru.testtask.taskuser.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.config.security.jwt.RoleCode;
import ru.testtask.taskuser.dao.repositories.UsersAccountRepository;
import ru.testtask.taskuser.model.UsersAccount;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminSecurityServiceImpl implements AdminSecurityService {

    final UsersAccountRepository usersAccountRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse registerAccount(RegisterRequest request) {
        checkUserAccountAlreadyExist(request);

        UsersAccount usersAccount = new UsersAccount();
        usersAccount.setLogin(request.getLogin());
        usersAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        for (RoleCode roleCode : request.getRoles()) {
            usersAccount.addRole(roleCode);
        }

        usersAccountRepository.save(usersAccount);
        return RegisterResponse.builder().login(usersAccount.getLogin()).build();
    }

    private void checkUserAccountAlreadyExist(RegisterRequest request) {
        usersAccountRepository.findByLogin(request.getLogin())
            .ifPresent((usersAccount ->
            {throw new IllegalStateException("User with this login already exist");}));
    }
}
