package com.inovabook.web.exception;

public class AuthException extends RuntimeException {

    public AuthException() { }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}