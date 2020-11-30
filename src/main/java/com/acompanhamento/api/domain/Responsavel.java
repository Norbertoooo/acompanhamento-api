package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel {

    @Id
    private Long id;

    private String nomeCompleto;

    private Integer idade;

    private Long telefone;

    private String cpf;

    private String parentesco;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private Login login;

}
