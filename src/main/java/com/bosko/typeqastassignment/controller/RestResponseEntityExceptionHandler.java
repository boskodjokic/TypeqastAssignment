package com.bosko.typeqastassignment.controller;

import com.bosko.typeqastassignment.exceptions.BadRequestException;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<Object>("Resource with specified parameters is not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception exception, WebRequest request) {
        return new ResponseEntity<Object>("Resource with same parameters already exists", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
