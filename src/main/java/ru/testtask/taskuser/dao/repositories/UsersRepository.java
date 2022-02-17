package ru.testtask.taskuser.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.testtask.taskuser.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
}
