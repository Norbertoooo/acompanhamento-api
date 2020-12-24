package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.repository.TerapeutaRepository;
import com.acompanhamento.api.service.TerapeutaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.PACIENTE_NAO_ENCONTRADO;
import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.TERAPEUTA_NAO_ENCONTRADO;

@Service
public class TerapeutaServiceImpl implements TerapeutaService {

    private final TerapeutaRepository terapeutaRepository;

    public TerapeutaServiceImpl(TerapeutaRepository terapeutaRepository) {
        this.terapeutaRepository = terapeutaRepository;
    }

    @Override
    public Terapeuta cadastrarLoginTerapeuta(Login login) {
        return terapeutaRepository.save(new Terapeuta(login));
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
    public Terapeuta buscarTerapeutaPeloCrp(Long crp) throws Exception {
        return terapeutaRepository.findByCrp(crp).orElseThrow(() -> new Exception(TERAPEUTA_NAO_ENCONTRADO));
    }

    @Override
    public Terapeuta atualizarInformacoes(Terapeuta terapeutaAlterado, String email) throws Exception {
        Terapeuta terapeuta = buscarTerapeutaPorEmail(email);
        terapeuta.setNomeCompleto(terapeutaAlterado.getNomeCompleto());
        terapeuta.setCrp(terapeutaAlterado.getCrp());
        terapeuta.setCpf(terapeutaAlterado.getCpf());
        return terapeutaRepository.save(terapeuta);
    }

}
