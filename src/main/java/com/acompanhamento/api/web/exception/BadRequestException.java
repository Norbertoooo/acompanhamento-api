package com.acompanhamento.api.web.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
