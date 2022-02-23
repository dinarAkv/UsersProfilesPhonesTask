package ru.testtask.taskuser.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.config.security.jwt.TaskUserDetails;
import ru.testtask.taskuser.dao.repositories.UsersAccountRepository;
import ru.testtask.taskuser.excpetions.UserAccountNotFoundException;
import ru.testtask.taskuser.model.UsersAccount;

import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TaskUserDetailsService implements UserDetailsService {

    final UsersAccountRepository usersAccountRepository;

    @Override
    public TaskUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersAccount userAccount = usersAccountRepository.findByLogin(username)
                .orElseThrow(() -> new UserAccountNotFoundException(username));
        return toUserDetails(userAccount);
    }

    private TaskUserDetails toUserDetails(UsersAccount usersAccount) {
        TaskUserDetails userDetails = new TaskUserDetails();
        userDetails.setLogin(usersAccount.getLogin());
        userDetails.setPassword(usersAccount.getPassword());
        Set<SimpleGrantedAuthority> auth = usersAccount.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleCode().name()))
                .collect(Collectors.toSet());
        userDetails.setGrantedAuthorities(auth);
        return userDetails;
    }
}
