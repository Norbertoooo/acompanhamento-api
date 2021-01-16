package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.repository.TerapeutaRepository;
import com.acompanhamento.api.resource.dto.CadastroTerapeutaDTO;
import com.acompanhamento.api.service.TerapeutaService;
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
    public Terapeuta cadastrarLoginTerapeuta(Terapeuta terapeuta) {
        terapeuta.getLogin().setPerfil(TERAPEUTA);
        terapeuta.getLogin().setSenha(passwordEncoder.encode(terapeuta.getLogin().getSenha()));
        return terapeutaRepository.save(terapeuta);
    }

    @Override
    public Terapeuta buscarTerapeutaPorNome(String nome) throws Exception {
        return terapeutaRepository.findByNomeCompleto(nome).orElseThrow(() -> new Exception(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta buscarTerapeutaPorEmail(String email) throws Exception {
        return terapeutaRepository.findByLogin_Email(email).orElseThrow(() -> new Exception(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta buscarTerapeutaPeloCrfa(Long crfa) throws Exception {
        return terapeutaRepository.findByCrfa(crfa).orElseThrow(() -> new Exception(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta atualizarInformacoes(Terapeuta terapeutaAlterado, String email) throws Exception {
        Terapeuta terapeuta = buscarTerapeutaPorEmail(email);
        terapeuta.setNomeCompleto(terapeutaAlterado.getNomeCompleto());
        terapeuta.setCrfa(terapeutaAlterado.getCrfa());
        terapeuta.setCpf(terapeutaAlterado.getCpf());
        return terapeutaRepository.save(terapeuta);
    }

}
