package com.acompanhamento.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaDTO {

    private Long id;

    private String observacoes;

    private Long nivel;

    private String sensibilidade;

    private Long duracao;

    private Long pontuacao;

}
