package ru.testtask.taskuser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@Entity
@Table(name = "PHONES", schema = "TASKUSER",
        indexes = @Index(name = "PHONES_VAL_UC_IX", columnList = "PHONE,USER_ID", unique = true))
@NoArgsConstructor
public class Phones {

    public Phones(String phone) {
        this.phone = phone;
    }

    @Id
    @SequenceGenerator(
            name = "PHONES_SEQ_GENERATOR",
            schema = "TASKUSER",
            sequenceName = "PHONES_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONES_SEQ_GENERATOR")
    Long id;

    @Column(name = "PHONE", nullable = false)
    String phone;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    Users user;


}
