package com.project.taskManagement.exception;

public class UserAlreadyPresentException extends RuntimeException {
    public UserAlreadyPresentException(String username) {
        super("The user with username '" + username + "' already exists.");
    }
    public UserAlreadyPresentException(String email,boolean isEmail) {
        super("The user with email '" + email + "' already exists.");
    }
}
