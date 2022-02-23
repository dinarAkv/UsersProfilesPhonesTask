package ru.testtask.taskuser.excpetions;

public class IncorrectLoginOrPasswordException extends RuntimeException {
    public IncorrectLoginOrPasswordException() {
        super("Incorrect login or password");
    }
}
