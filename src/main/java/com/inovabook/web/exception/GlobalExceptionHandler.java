package com.inovabook.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex,
                                    Model model) {
        model.addAttribute("fileError", "Error while processing file: " + ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException ex,
                                         Model model) {
        model.addAttribute("fileError", "The uploaded file is too large.");
        return "error-page";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex,
                                         Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(FileStorageException.class)
        public String handleFileStorageException(FileStorageException ex,
                                                 Model model,
                                                 HttpServletRequest request) {
        String referer = request.getHeader("Referer");
            model.addAttribute("fileError", ex.getMessage());
            return "redirect:" + (referer !=null ? referer: "/error");
        }

    @ExceptionHandler(CourseException.class)
        public String handleCourseException(CourseException ex,
                                            Model model) {
        String userMessage;
        if (ex instanceof CourseNotFoundException notFound) {
            userMessage = ex.getMessage();
        } else {
            userMessage = "Erro inesperado " + ex.getMessage();
        }
        model.addAttribute("errorMessage", userMessage);
        return "error-page";
        }

    @ExceptionHandler(LessonException.class)
    public String handleLessonException(LessonException ex,
                                        Model model) {
        String userMessage;
        if (ex instanceof LessonNotFoundException notFound) {
            userMessage = ex.getMessage();
        } else {
            userMessage = "Erro inesperado " + ex.getMessage();
        }
        model.addAttribute("errorMessage", userMessage);
        return "error-page";
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public String handleOptimisticLock(ObjectOptimisticLockingFailureException ex,
                                       RedirectAttributes attrs) {
        attrs.addFlashAttribute("error",
                "Outro usuário está modificando essa lição, tente novamente mais tarde.");
        return "redirect:/lesson/{id}/new/";
    }

    /*private Long extractIdFromException(ObjectOptimisticLockingFailureException ex) {
        // The message contains the entity class and id, e.g. "...Lesson#8"
        String msg = ex.getMessage();
        Matcher m = Pattern.compile("#(\\d+)").matcher(msg);
        return m.find() ? Long.valueOf(m.group(1)) : null;
    }*/
}



