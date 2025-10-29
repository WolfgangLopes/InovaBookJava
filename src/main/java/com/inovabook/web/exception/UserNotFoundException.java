package com.inovabook.web.exception;

import java.util.UUID;

public class UserNotFoundException extends UserException {

    private final UUID id;

    public UserNotFoundException(UUID id) {
        super("Curso não encontrado, id:"+id);
        this.id = id;
    }

    public UUID getUserId(){
        return id;
    }
}
