package ru.testtask.taskuser.excpetions;

public class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(String login) {
        super(String.format("User account with login %s not found", login));
    }
}
