package com.inovabook.web.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    protected UserException(String message, Throwable cause){
        super(message, cause);
    }
}
