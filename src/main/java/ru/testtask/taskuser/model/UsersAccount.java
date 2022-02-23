package ru.testtask.taskuser.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.testtask.taskuser.config.security.jwt.RoleCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS_ACCOUNT", schema = "TASKUSER",
        indexes = {@Index(name = "USERS_ACCOUNT_LOGIN_UC_IX", columnList = "LOGIN", unique = true)})
@NoArgsConstructor
public class UsersAccount {

    @Id
    @SequenceGenerator(
            name = "USERS_ACCOUNT_SEQ_GENERATOR",
            schema = "TASKUSER",
            sequenceName = "USERS_ACCOUNT_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ACCOUNT_SEQ_GENERATOR")
    Long id;

    @Column(name = "LOGIN", nullable = false)
    String login;

    @Column(name = "PASSWORD", nullable = false)
    String password;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<UsersRole> roles = new HashSet<>();

    public void addRole(RoleCode roleCode) {
        UsersRole usersRole = new UsersRole(roleCode);
        usersRole.setUserAccount(this);
        roles.add(usersRole);
    }
}
