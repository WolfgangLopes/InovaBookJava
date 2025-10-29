package com.inovabook.web.exception;

public class UsernameAlreadyUsedException extends AuthException {

    public UsernameAlreadyUsedException() {
        super("Nome de usuário já esta em uso");
    }

    public UsernameAlreadyUsedException(String username) {
        super("Nome de usuário '" + username + "' já está em uso");
    }
}