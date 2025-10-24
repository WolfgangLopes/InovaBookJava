package com.inovabook.web.exception;

public class CourseNotFoundException extends CourseException {

    private final long id;

    public CourseNotFoundException(long id) {
        super("Curso não encontrado, id:"+id);
        this.id = id;
    }

    public long getCourseId(){
        return id;
    }
}
