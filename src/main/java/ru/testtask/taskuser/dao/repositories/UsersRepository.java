package ru.testtask.taskuser.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.testtask.taskuser.model.Users;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, Long>, JpaSpecificationExecutor<Users> {
}
