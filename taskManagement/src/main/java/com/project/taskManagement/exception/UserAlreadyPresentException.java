package com.project.taskManagement.exception;

public class UserAlreadyPresentException extends RuntimeException {
    public UserAlreadyPresentException(String username) {
        super("The user with username '" + username + "' already exists.");
    }
}
