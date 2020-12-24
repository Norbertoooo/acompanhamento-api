package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observacoes;

    private Long nivel;

    private String sensibilidade;

    private Long duracao;

    private Long pontuacao;

    @OneToOne( mappedBy = "ficha", fetch = FetchType.LAZY)
    private Paciente paciente;
}
