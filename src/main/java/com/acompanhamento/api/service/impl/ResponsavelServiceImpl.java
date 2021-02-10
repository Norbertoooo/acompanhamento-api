package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Responsavel;
import com.acompanhamento.api.repository.ResponsavelRepository;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.RESPONSAVEL_NAO_ENCONTRADO;

@Service
public class ResponsavelServiceImpl implements ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    public ResponsavelServiceImpl(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public Responsavel cadastrarLoginResponsavel(Login login) {
        return responsavelRepository.save(new Responsavel(login));
    }

    @Override
    public List<Responsavel> listarTodosResponsaveis() {
        return responsavelRepository.findAll();
    }

    @Override
    public Responsavel buscarResponsavelPeloNome(String nome) throws Exception {
        return responsavelRepository.findByNomeCompleto(nome).orElseThrow( () -> new Exception(RESPONSAVEL_NAO_ENCONTRADO));
    }

    @Override
    public Responsavel buscarResponsavelPeloEmail(String email) {
        return responsavelRepository.findByLoginEmail(email).orElseThrow( () -> new ResourceNotFoundException("Email não encontrado"));
    }
}
