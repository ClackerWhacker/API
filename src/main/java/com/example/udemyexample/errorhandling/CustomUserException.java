package com.example.udemyexample.errorhandling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

public class CustomUserException extends Exception {

    private Date timestamp;
    private String message;
    private String details;

    public CustomUserException(String message) {
        this.message = message;
    }

    public CustomUserException(Date timestamp, String message, String details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return "CustomUserException{" +
                "timestamp=" + timestamp +
                ", details='" + details + '\'' +
                "message='" + message +
                '}';
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
