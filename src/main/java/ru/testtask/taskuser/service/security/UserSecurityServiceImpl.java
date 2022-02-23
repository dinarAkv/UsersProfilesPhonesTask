package ru.testtask.taskuser.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.config.security.jwt.JwtProvider;
import ru.testtask.taskuser.model.UsersAccount;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    final UserAccountService userAccountService;
    final JwtProvider jwtProvider;

    @Override
    public AuthResponse auth(AuthRequest request) {
        UsersAccount userAccount = userAccountService
                .findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userAccount.getLogin());
        return AuthResponse.builder().token(token).build();
    }
}
