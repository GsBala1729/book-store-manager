package com.finzly.bookmanager.exceptions;

public class BookMgrAuthException extends RuntimeException {
    public BookMgrAuthException(String msg, Throwable cause) {
        super(msg, cause);

    }
    public BookMgrAuthException(String msg) {
        super(msg);
    }
}
