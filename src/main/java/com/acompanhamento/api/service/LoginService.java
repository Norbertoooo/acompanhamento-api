package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Login;

import java.util.Optional;

public interface LoginService {
    Login save(Login login);
    Optional<Login> findByEmail(String email) throws Exception;
    boolean loginExistente(String email);
}
