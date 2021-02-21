package com.acompanhamento.api.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private Long telefone;

    private Long crfa;

    private String cpf;

    private String especialidade;

    private String formacao;

    private String loginEmail;

    private EnderecoDTO endereco;
}
