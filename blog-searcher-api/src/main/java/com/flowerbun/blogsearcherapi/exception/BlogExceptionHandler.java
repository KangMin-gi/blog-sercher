package com.flowerbun.blogsearcherapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> toCustomerException(CustomerException ce) {
        ErrorResponse response = new ErrorResponse(ce);
        return new ResponseEntity<>(response, ce.getHttpStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> IllegalArgument(IllegalArgumentException iae) {
        ErrorResponse response = new ErrorResponse(iae);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
