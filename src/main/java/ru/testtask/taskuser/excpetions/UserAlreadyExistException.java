package ru.testtask.taskuser.excpetions;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String login) {
        super(String.format("User with email %s already exist", login));
    }

}
