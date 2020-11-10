package com.example.udemyexample.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

public class ControllerErrorHandling {

//    Specify the response status
//    Specify the exception handler
//    Adds this to the response
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomUserException.class)
    public String employeeNotFoundError(CustomUserException exception){
        return exception.getMessage();
    }

}
