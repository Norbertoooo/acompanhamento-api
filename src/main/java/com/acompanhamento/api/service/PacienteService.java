package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Paciente;

import java.util.List;

public interface PacienteService {

    Paciente buscarPacientePeloNome(String nome, String email) throws Exception;
    List<Paciente> listarPacientesPeloEmailDoTerapeuta(String email);
    Paciente cadastrarPaciente(String email, Paciente paciente) throws Exception;
    void removerPacientePeloNome(String nome, String email);
}
