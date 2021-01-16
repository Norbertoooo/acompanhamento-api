package com.acompanhamento.api.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespostaAutenticacaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private LoginDTO login;
    private String token;
}
