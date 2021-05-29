package com.bosko.typeqastassignment.controller;

import com.bosko.typeqastassignment.exceptions.BadRequestException;
import com.bosko.typeqastassignment.exceptions.JsonResponse;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JsonResponse handleBadRequestException() {
        return new JsonResponse("Resource with same parameters already exists");
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JsonResponse handleNotFoundException() {
        return new JsonResponse( "Resource with specified parameters is not found. Please check the data entered and try again.");
    }

}
