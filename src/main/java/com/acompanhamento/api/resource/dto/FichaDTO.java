package com.acompanhamento.api.resource.dto;

import lombok.Data;

@Data
public class FichaDTO {

    private Long id;

    private String observacoes;

    private Long nivel;

    private String sensibilidade;

    private Long duracao;

    private Long pontuacao;

}
