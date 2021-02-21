package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.repository.TerapeutaRepository;
import com.acompanhamento.api.web.exception.ResourceNotFoundException;
import com.acompanhamento.api.service.TerapeutaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.acompanhamento.api.domain.Perfil.TERAPEUTA;
import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.TERAPEUTA_NAO_ENCONTRADO;

@Service
public class TerapeutaServiceImpl implements TerapeutaService {

    private final TerapeutaRepository terapeutaRepository;
    private final PasswordEncoder passwordEncoder;

    public TerapeutaServiceImpl(TerapeutaRepository terapeutaRepository, PasswordEncoder passwordEncoder) {
        this.terapeutaRepository = terapeutaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Terapeuta cadastrarTerapeuta(Terapeuta terapeuta) {
        terapeuta.getLogin().setPerfil(TERAPEUTA);
        terapeuta.getLogin().setSenha(passwordEncoder.encode(terapeuta.getLogin().getSenha()));
        return terapeutaRepository.save(terapeuta);
    }

    @Override
    public Terapeuta buscarTerapeutaPorNome(String nome) {
        return terapeutaRepository.findByNomeCompleto(nome)
                .orElseThrow(() -> new ResourceNotFoundException(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta buscarTerapeutaPorEmail(String email) {
        return terapeutaRepository.findByLogin_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta buscarTerapeutaPeloCrfa(Long crfa) {
        return terapeutaRepository.findByCrfa(crfa)
                .orElseThrow(() -> new ResourceNotFoundException(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta atualizarInformacoes(Terapeuta terapeutaAlterado, String email) {
        Terapeuta terapeuta = buscarTerapeutaPorEmail(email);
        terapeuta.setCpf(terapeutaAlterado.getCpf());
        terapeuta.setCrfa(terapeutaAlterado.getCrfa());
        terapeuta.setNomeCompleto(terapeutaAlterado.getNomeCompleto());
        terapeuta.setTelefone(terapeutaAlterado.getTelefone());
        terapeuta.setFormacao(terapeutaAlterado.getFormacao());
        terapeuta.setEspecialidade(terapeutaAlterado.getEspecialidade());
        terapeuta.setEndereco(terapeutaAlterado.getEndereco());
        terapeuta.setDataNascimento(terapeutaAlterado.getDataNascimento());
        return terapeutaRepository.save(terapeuta);
    }

    @Override
    public Page<Terapeuta> listarTerapeutas(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return terapeutaRepository.findAll(pageable);
    }

}
