package com.acompanhamento.api.web.exception;

public class EmailException extends RuntimeException {
    public EmailException(String mensagem) {
        super(mensagem);
    }
}
