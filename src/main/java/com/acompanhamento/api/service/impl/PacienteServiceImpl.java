package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.repository.PacienteRepository;
import com.acompanhamento.api.resource.exception.ResourceNotFoundException;
import com.acompanhamento.api.service.PacienteService;
import com.acompanhamento.api.service.TerapeutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TerapeutaService terapeutaService;

    @Override
    public Paciente buscarPacientePeloNome(String nome, String email) {
        return pacienteRepository.findByNomeCompletoAndTerapeuta_Login_Email(nome, email)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
    }

    @Override
    public Page<Paciente> listarPacientesPeloEmailDoTerapeuta(String email, Integer page, Integer count) {
        Pageable pages = PageRequest.of(page, count);
        return pacienteRepository.findAllByTerapeuta_Login_Email(email, pages);
    }

    @Override
    public Paciente cadastrarPaciente(String email, Paciente paciente) throws Exception {
        paciente.setTerapeuta(terapeutaService.buscarTerapeutaPorEmail(email));
        return pacienteRepository.save(paciente);
    }

    @Override
    public void removerPacientePeloNome(String nome, String email) {
        pacienteRepository.deleteByNomeCompletoAndTerapeuta_Login_Email(nome, email);
    }
}
