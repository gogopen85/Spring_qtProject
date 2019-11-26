package com.example.demo.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.rmi.UnexpectedException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UnauthorizedException.class)
    public String UnauthorizedExceptionHandler(Exception ex){
        return "user/login";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String NoHandlerFoundExceptionHandler(Exception ex){
        return "error/404";
    }

    @ExceptionHandler(UnexpectedException.class)
    public String UnexpectedExceptionHandler(Exception ex){
        return "error/500";
    }
}
