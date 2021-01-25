package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Paciente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PacienteService {

    Paciente buscarPacientePeloNome(String nome, String emailTerapeuta) throws Exception;

    Page<Paciente> listarPacientesPeloEmailDoTerapeuta(String emailTerapeuta, Integer page, Integer count);

    Paciente cadastrarPaciente(String emailTerapeuta, Paciente paciente) throws Exception;

    void removerPacientePeloNome(String nome, String emailTerapeuta);

    void removerPacientesPeloId(List<Long> pacientesId, String emailTerapeuta);
}
