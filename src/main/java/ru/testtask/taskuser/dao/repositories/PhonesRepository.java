package ru.testtask.taskuser.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.testtask.taskuser.model.Phones;

import java.util.Optional;

@Repository
public interface PhonesRepository extends CrudRepository<Phones, Long> {

    Optional<Phones> findByPhoneAndUserId(String value, Long usersId);
}
