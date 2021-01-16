package com.acompanhamento.api.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private Long id;

    private String nomeCompleto;

    private Integer idade;

    private Long fichaId;

}
