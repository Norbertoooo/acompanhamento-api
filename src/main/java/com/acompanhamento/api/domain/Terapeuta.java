package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Terapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private Integer idade;

    private Long telefone;

    private Long crp;

    private String cpf;

    private String especialidade;

    private String formacao;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private Login login;

}
