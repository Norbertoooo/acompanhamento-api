package com.acompanhamento.api.service;

import com.acompanhamento.api.domain.Endereco;

public interface EnderecoService {

    Endereco buscarEnderecoPeloId(Long id);
    Endereco buscarEnderecoPeloCpf(String cpf);

}
