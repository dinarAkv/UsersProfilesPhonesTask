package ru.testtask.taskuser.config.security.jwt;

import lombok.Getter;

@Getter
public enum RoleCode {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    String roleWithoutPrefix;

    RoleCode(String roleWithoutPrefix) {
        this.roleWithoutPrefix = roleWithoutPrefix;
    }
}
