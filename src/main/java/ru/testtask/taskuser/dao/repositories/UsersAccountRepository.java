package ru.testtask.taskuser.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.testtask.taskuser.model.UsersAccount;

import java.util.Optional;

@Repository
public interface UsersAccountRepository extends CrudRepository<UsersAccount, Long> {

    Optional<UsersAccount> findByLogin(String login);
}
