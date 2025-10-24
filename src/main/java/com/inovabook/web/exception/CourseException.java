package com.inovabook.web.exception;

public abstract class CourseException extends RuntimeException {

    protected CourseException(String message) {
        super(message);
    }

    protected CourseException(String message, Throwable cause){
        super(message, cause);
    }


}
