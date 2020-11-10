package com.example.udemyexample.errorhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// this shares this across multiple controller classes
@RestControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomUserException.class)
    public final ResponseEntity<CustomUserException> handleAllExceptions(CustomUserException ex, WebRequest webRequest){
        System.out.println(ex.toString());

        CustomUserException customUserException =
                new CustomUserException(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(customUserException, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomUserException customUserException =
                new CustomUserException(new Date(), "Validation Failed ", request.getDescription(false));
        return new ResponseEntity(customUserException, HttpStatus.BAD_REQUEST);
    }



}
