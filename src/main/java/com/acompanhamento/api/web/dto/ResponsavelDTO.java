package com.acompanhamento.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelDTO {

    private Long id;

    private String nomeCompleto;
}
