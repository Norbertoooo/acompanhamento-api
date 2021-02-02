package com.acompanhamento.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private Long id;

    private Long cep;

    private Long numero;

    private String rua;

    private String bairro;

    private String complemento;

    private String cidade;

    private String estado;
}
