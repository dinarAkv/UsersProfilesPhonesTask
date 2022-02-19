package ru.testtask.taskuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Set;

@ActiveProfiles("dev")
@SpringBootTest(classes = SpringBootLocalRunner.class)
@Transactional
public abstract class AbstractLocalTest {

    @Autowired
    protected UsersRepository usersRepository;

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
}
