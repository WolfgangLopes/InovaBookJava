package com.inovabook.web.exception;

public class EmailNotFoundException extends UserException {

    private final String email;

    public EmailNotFoundException(String email) {
        super("Curso não encontrado, email:"+email);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}
