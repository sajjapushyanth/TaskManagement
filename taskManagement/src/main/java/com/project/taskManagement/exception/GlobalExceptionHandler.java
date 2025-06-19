package com.project.taskManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<String> handleUserAlreadyPresentException(UserAlreadyPresentException ex) {
        // Log the exception or perform any other necessary actions
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // Return a user-friendly message or error response
    }
}
