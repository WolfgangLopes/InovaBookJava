package com.inovabook.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex,
                                    Model model) {
        model.addAttribute("fileError", "Error while processing file: " + ex.getMessage());
        return "error-page"; // name of your Thymeleaf error template
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
    }

