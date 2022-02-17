package ru.testtask.taskuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        Users user = new Users();
        user.setName("Stepan");
        user.setAge(35);
        user.setEmail("stepanIM@mail.com");
        user.setCash("234567.12");

        user.addPhone("89211112233");
        user.addPhone("89211112234");
        user.addPhone("89211112235");
        return usersRepository.save(user);
    }
}
