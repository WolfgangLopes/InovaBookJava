package com.inovabook.web.exception;

public abstract class LessonException extends RuntimeException {

    protected LessonException(String message) {
        super(message);
    }

    protected LessonException(String message, Throwable cause){
        super(message, cause);
    }


}
