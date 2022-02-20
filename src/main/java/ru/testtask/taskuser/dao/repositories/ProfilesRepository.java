package ru.testtask.taskuser.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.testtask.taskuser.model.Profiles;

import java.math.BigDecimal;
import java.util.Collection;

@Repository
public interface ProfilesRepository extends CrudRepository<Profiles, Long> {

    @Query("SELECT p FROM Profiles p \n" +
            "WHERE (p.cash / p.initCash) < :maxIncomeFactor")
    Collection<Profiles> getAvailableProfileForAccrualInterest(BigDecimal maxIncomeFactor);

}
