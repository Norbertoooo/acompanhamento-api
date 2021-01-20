package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Terapeuta;
import org.springframework.data.domain.Page;

public interface TerapeutaService {
    Terapeuta cadastrarLoginTerapeuta(Terapeuta terapeuta);

    Terapeuta buscarTerapeutaPorNome(String nome) throws Exception;

    Terapeuta buscarTerapeutaPorEmail(String email) throws Exception;

    Terapeuta buscarTerapeutaPeloCrfa(Long crfa) throws Exception;

    Terapeuta atualizarInformacoes(Terapeuta terapeuta, String email) throws Exception;

    Page<Terapeuta> listarTerapeutas(Integer page, Integer count);
}
