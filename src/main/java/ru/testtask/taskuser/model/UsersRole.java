package ru.testtask.taskuser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.testtask.taskuser.config.security.jwt.RoleCode;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USERS_ROLE", schema = "TASKUSER")
@NoArgsConstructor
public class UsersRole {

    public UsersRole(RoleCode roleCode) {
        this.roleCode = roleCode;
    }

    @Id
    @SequenceGenerator(
            name = "USERS_ROLE_SEQ_GENERATOR",
            schema = "TASKUSER",
            sequenceName = "USERS_ROLE_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ROLE_SEQ_GENERATOR")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    RoleCode roleCode;

    @ManyToOne
    @JoinColumn(name = "USER_ACCOUNT_ID")
    UsersAccount userAccount;
}
