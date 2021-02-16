package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Responsavel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResponsavelService {
    Responsavel cadastrarLoginResponsavel(Login login);
    Page<Responsavel> listarTodosResponsaveis(Integer page, Integer count);
    Responsavel buscarResponsavelPeloNome(String nome) throws Exception;
    Responsavel buscarResponsavelPeloEmail(String email);
}
