package com.jaga.usermanagement.exception;

import com.jaga.usermanagement.view.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;

@ControllerAdvice
public class UserManagementExceptionHandler {

    /* Reused existing Exception Classes But in real time we should create our own classes */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("ERROR", String.format("User %s not found ", ex.getMessage()));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleExistUser(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("ERROR", String.format("User %s exists ", ex.getMessage()));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginFailure(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("ERROR", "Login Failed");
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }

}
