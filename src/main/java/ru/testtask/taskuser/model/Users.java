package ru.testtask.taskuser.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@Entity
@Table(name = "USERS", schema = "TASKUSER",
        indexes = { @Index(name = "USERS_EMAIL_UC_IX", columnList = "EMAIL", unique = true),
                    @Index(name = "USERS_NAME_IX", columnList = "NAME"),
                    @Index(name = "USERS_AGE_IX", columnList = "AGE")})
@NoArgsConstructor
public class Users {

    @Id
    @SequenceGenerator(
            name = "USERS_SEQ_GENERATOR",
            schema = "TASKUSER",
            sequenceName = "USERS_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GENERATOR")
    Long id;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "AGE", nullable = false)
    int age;

    @Column(name = "EMAIL", nullable = false, unique = true)
    String email;

    @Setter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    Profiles profiles;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Phones> phones = new HashSet<>();

    public void addPhone(String value) {
        Phones phone = new Phones(value);
        phones.add(phone);
        phone.setUser(this);
    }

    public void setCash(String cash) {
        if (this.profiles == null) {
            this.profiles = new Profiles(new BigDecimal(cash));
            profiles.setUser(this);
            return;
        }

        this.profiles.setCash(new BigDecimal(cash));
    }

    public void deletePhone(String phoneToDelete) {
        Iterator<Phones> iterator = phones.iterator();
        for (;iterator.hasNext();) {
            Phones nextPhone = iterator.next();
            if (nextPhone.getPhone().equals(phoneToDelete)) {
                iterator.remove();
            }
        }
    }

    public void replacePhones(Collection<String> phoneVals) {
        Iterator<Phones> iterator = this.phones.iterator();
        for (;iterator.hasNext();) {
            Phones phone = iterator.next();
            if (!phoneVals.contains(phone.getPhone())) {
                iterator.remove();
            }
        }

        Set<String> currentPhones = this.phones.stream().map(Phones::getPhone)
                .collect(Collectors.toSet());
        for (String phoneVal : phoneVals) {
            if (!currentPhones.contains(phoneVal)) {
                addPhone(phoneVal);
            }
        }
    }
}
