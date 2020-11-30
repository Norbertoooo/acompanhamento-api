package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ficha {

    @Id
    private Long id;

    private String observacoes;

    private String nivel;

    private String sensibilidade;

    private String duracao;

    private Long pontuacao;
}
