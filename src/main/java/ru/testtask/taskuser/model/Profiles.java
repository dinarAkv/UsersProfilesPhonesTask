package ru.testtask.taskuser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@Entity
@Table(name = "PROFILES", schema = "TASKUSER")
@NoArgsConstructor
public class Profiles {

    public Profiles(BigDecimal cash) {
        this.cash = cash;
    }

    @Id
    @SequenceGenerator(
            name = "PROFILES_SEQ_GENERATOR",
            schema = "TASKUSER",
            sequenceName = "PROFILES_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ_GENERATOR")
    Long id;

    @Column(name = "CASH", nullable = false)
    BigDecimal cash;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    Users user;
}
