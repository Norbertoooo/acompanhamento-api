package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Responsavel;

import java.util.List;

public interface ResponsavelService {
    Responsavel cadastrarLoginResponsavel(Login login);
    List<Responsavel> listarTodosResponsaveis();
    Responsavel buscarResponsavelPeloNome(String nome) throws Exception;
}
