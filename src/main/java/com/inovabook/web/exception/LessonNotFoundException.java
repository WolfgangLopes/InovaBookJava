package com.inovabook.web.exception;

public class LessonNotFoundException extends LessonException {

    private final long id;

    public LessonNotFoundException(long id) {
        super("Curso não encontrado, id:"+id);
        this.id = id;
    }

    public long getCourseId(){
        return id;
    }
}
