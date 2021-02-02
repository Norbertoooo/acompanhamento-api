package com.acompanhamento.api.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroTerapeutaDTO {

    private Long id;

    @NotBlank
    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotNull
    private Long telefone;

    @NotNull
    private Long crfa;

    @NotBlank
    private String cpf;

    @NotBlank
    private String especialidade;

    @NotBlank
    private String formacao;

    @NotNull
    private LoginDTO login;

    @NotNull
    private EnderecoDTO endereco;

}
