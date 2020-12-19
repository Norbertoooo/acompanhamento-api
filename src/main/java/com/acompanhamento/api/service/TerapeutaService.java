package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Terapeuta;

public interface TerapeutaService {
    Terapeuta cadastrarLoginTerapeuta(Login login);
    Terapeuta buscarTerapeutaPorNome(String nome) throws Exception;
}
