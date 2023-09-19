package com.finzly.bookmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoBooksFoundException.class)
    public ResponseEntity<String> handleNoBooksFoundException(NoBooksFoundException exception) {
        String errorMessage = "No Books Aavailable: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UsersNotAvailableException.class)
    public ResponseEntity<String> handleUsersNotFoundException(UsersNotAvailableException exception) {
        String errorMessage = "Users Not Aavailable: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        String errorMessage = "IllegalArgument: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
