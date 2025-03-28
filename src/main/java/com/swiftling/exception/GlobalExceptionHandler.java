package com.swiftling.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public String handleGenericExceptions(Throwable exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", "Action failed: An error occurred!");
        return "error-global";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String noResourceFoundExceptionHandler(NoResourceFoundException exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", "The page you are looking for doesn't exist.");
        return "error-global";
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    public String accessDeniedExceptionHandler(AccessDeniedException exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", "Access Denied!");
        return "error-global";
    }

}