package com.facebook.api.exceptionhandling;

import org.springframework.http.HttpStatus;
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


}
