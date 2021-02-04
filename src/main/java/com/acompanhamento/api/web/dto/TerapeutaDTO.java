package com.acompanhamento.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerapeutaDTO {

    private Long id;

    private String nomeCompleto;

    private Date dataNascimento;

    private Long telefone;

    private Long crfa;

    private String cpf;

    private String especialidade;

    private String formacao;

    private String loginEmail;

    private Long enderecoId;

    private EnderecoDTO endereco;
}
