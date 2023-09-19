package com.finzly.bookmanager.exceptions;

public class UsersNotAvailableException extends RuntimeException {
    public UsersNotAvailableException(String message) {
        super(message);
    }
}
