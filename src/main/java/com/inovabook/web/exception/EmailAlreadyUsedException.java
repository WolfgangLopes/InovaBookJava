package com.inovabook.web.exception;

public class EmailAlreadyUsedException extends AuthException {

    public EmailAlreadyUsedException() {
        super("Email já esta registrado");
    }

    public EmailAlreadyUsedException(String email) {
        super("Email '" + email + "' já está registrado");
    }
}