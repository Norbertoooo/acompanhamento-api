package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Responsavel;
import com.acompanhamento.api.repository.ResponsavelRepository;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.web.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Responsavel> listarTodosResponsaveis(Integer page, Integer count) {
        Pageable pages = PageRequest.of(page, count);
        return responsavelRepository.findAll(pages);
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
