package com.acompanhamento.api.web.dto;

import com.acompanhamento.api.domain.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarPacienteDTO {

    private String nomeCompleto;

    private Integer idade;

    private List<Responsavel> responsaveis;

}
