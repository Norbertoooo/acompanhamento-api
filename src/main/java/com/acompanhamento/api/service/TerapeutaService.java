package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.domain.Terapeuta;

public interface TerapeutaService {
    Terapeuta cadastrarLoginTerapeuta(Login login);
    Terapeuta buscarTerapeutaPorNome(String nome) throws Exception;
    Terapeuta buscarTerapeutaPorEmail(String email) throws Exception;
    Terapeuta buscarTerapeutaPeloCrp(Long crp) throws Exception;
    Terapeuta atualizarInformacoes(Terapeuta terapeuta, String email) throws Exception;
}
