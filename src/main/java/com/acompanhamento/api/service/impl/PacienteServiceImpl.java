package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.repository.PacienteRepository;
import com.acompanhamento.api.service.EmailService;
import com.acompanhamento.api.service.PacienteService;
import com.acompanhamento.api.service.TerapeutaService;
import com.acompanhamento.api.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.acompanhamento.api.domain.Perfil.RESPONSAVEL;
import static com.acompanhamento.api.shared.Constantes.Variaveis.SENHA_PADRAO_RESPONSAVEL;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TerapeutaService terapeutaService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private EmailService emailService;

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
        paciente.getResponsaveis().forEach( responsavel -> {
            responsavel.getLogin().setPerfil(RESPONSAVEL);
            responsavel.getLogin().setSenha(bcryptEncoder.encode(SENHA_PADRAO_RESPONSAVEL));
        });
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        paciente.getResponsaveis().forEach(responsavel -> {
            emailService.enviar(responsavel.getLogin(), responsavel.getLogin().getEmail());
        });
        return pacienteSalvo;
    }

    @Override
    public void removerPacientePeloNome(String nome, String email) {
        pacienteRepository.deleteByNomeCompletoAndTerapeuta_Login_Email(nome, email);
    }

    @Override
    @Transactional
    public void removerPacientesPeloId(List<Long> pacientesId, String email) {
        pacientesId.forEach(id -> pacienteRepository.deleteById(id));
    }
}
