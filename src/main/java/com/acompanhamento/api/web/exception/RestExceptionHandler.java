package com.acompanhamento.api.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Exception resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Exception exception = new Exception();
        exception.setMensagem(ex.getMessage());
        exception.setStatus(HttpStatus.NOT_FOUND.value());
        exception.setUrl(request.getDescription(false).substring(4));
        return exception;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Exception badResquestException(BadRequestException ex, WebRequest request) {
        Exception exception = new Exception();
        exception.setMensagem(ex.getMessage());
        exception.setStatus(HttpStatus.BAD_REQUEST.value());
        exception.setUrl(request.getDescription(false).substring(4));
        return exception;
    }

}
