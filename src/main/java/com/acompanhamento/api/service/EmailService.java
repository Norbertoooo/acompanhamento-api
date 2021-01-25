package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Login;

public interface EmailService {

    void enviar(Login login, String email);

}
