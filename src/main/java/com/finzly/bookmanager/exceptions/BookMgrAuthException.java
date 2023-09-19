package com.finzly.bookmanager.exceptions;

import org.springframework.security.core.AuthenticationException;

public class BookMgrAuthException extends AuthenticationException {
    public BookMgrAuthException(String msg, Throwable cause) {
        super(msg, cause);

    }
    public BookMgrAuthException(String msg) {
        super(msg);
    }
}
