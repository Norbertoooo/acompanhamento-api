package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Endereco;
import com.acompanhamento.api.repository.EnderecoRepository;
import com.acompanhamento.api.service.EnderecoService;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Endereco buscarEnderecoPeloId(Long id) {
        return enderecoRepository.findById(id).orElseThrow();
    }

    @Override
    public Endereco buscarEnderecoPeloCpf(String cpf) {
        return enderecoRepository.findByTerapeuta_Cpf(cpf).orElseThrow();
    }
}
