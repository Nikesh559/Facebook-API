package com.facebook.api.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionMessage {

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ErrorMessage methodNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        return new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, "http media type not acceptable");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorMessage mediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        return new ErrorMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ErrorMessage methodNotAllowed(MethodNotAllowedException ex) {
        return new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorMessage methodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage invalidArgumentExp(MethodArgumentNotValidException ex) {
        List list = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorMessage(HttpStatus.BAD_REQUEST,list.toString());
    }
}
