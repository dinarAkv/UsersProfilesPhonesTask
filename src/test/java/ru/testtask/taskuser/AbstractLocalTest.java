package ru.testtask.taskuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.config.security.jwt.RoleCode;
import ru.testtask.taskuser.dao.repositories.UsersAccountRepository;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Users;
import ru.testtask.taskuser.model.UsersAccount;
import ru.testtask.taskuser.service.security.AdminSecurityService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Set;

@TestPropertySource(properties = "app.scheduling.enable=false")
@ActiveProfiles("dev")
@SpringBootTest(classes = SpringBootLocalRunner.class)
@Transactional
public abstract class AbstractLocalTest {

    @Autowired
    protected UsersRepository usersRepository;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected UsersAccountRepository usersAccountRepository;

    @Autowired
    protected AdminSecurityService adminSecurityService;

    @PersistenceContext
    protected EntityManager em;

    protected void flushAndClearSession() {
        em.flush();
        em.clear();
    }

    protected Users createUser() {
        return createUsers("Stepan", 35, "stepanIM@mail.com",
                "234567.12", Set.of("89211112233", "89211112234", "89211112235"));
    }

    protected Users createUsers(String name, int age, String email, String cash, Collection<String> phones) {
        Users user = new Users();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setCash(cash);

        phones.forEach(phone -> user.addPhone(phone));
        return usersRepository.save(user);
    }

    protected void registerUserAccount(String login, String password, Set<RoleCode> roles) {
        UsersAccount usersAccount = new UsersAccount();
        usersAccount.setLogin(login);
        usersAccount.setPassword(passwordEncoder.encode(password));
        for (RoleCode roleCode : roles) {
            usersAccount.addRole(roleCode);
        }

        usersAccountRepository.save(usersAccount);
    }
}
