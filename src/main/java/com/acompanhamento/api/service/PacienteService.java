package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Paciente;
import org.springframework.data.domain.Page;

public interface PacienteService {

    Paciente buscarPacientePeloNome(String nome, String email) throws Exception;

    Page<Paciente> listarPacientesPeloEmailDoTerapeuta(String email, Integer page, Integer count);

    Paciente cadastrarPaciente(String email, Paciente paciente) throws Exception;

    void removerPacientePeloNome(String nome, String email);

    void removerPacientePeloId(Long id, String email);
}
