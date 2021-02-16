package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Paciente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PacienteService {

    Paciente buscarPacientePeloNome(String nome, String emailTerapeuta) throws Exception;

    Page<Paciente> listarPacientes(Integer page, Integer count);

    Page<Paciente> listarPacientesPeloEmailDoTerapeuta(String emailTerapeuta, Integer page, Integer count);

    List<Paciente> listarPacientesPeloEmailDoResponsavel(String emailTerapeuta);

    Paciente cadastrarPaciente(String emailTerapeuta, Paciente paciente) throws Exception;

    void removerPacientesPeloId(List<Long> pacientesId, String emailTerapeuta);
}
