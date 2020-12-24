package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.service.TerapeutaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terapeutas/")
@CrossOrigin(origins = "*")
@Log4j2
public class TerapeutaResource {

    private final TerapeutaService terapeutaService;

    public TerapeutaResource(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @GetMapping("{email}")
    public Terapeuta buscarTerapeutaPorNome(@PathVariable String email) throws Exception {
        log.info("Requisição para buscar terapeuta pelo email de login: {}", email);
        return terapeutaService.buscarTerapeutaPorEmail(email);
    }

    @PutMapping("{email}")
    public Terapeuta atualizarTerapeuta(@PathVariable String email, @RequestBody Terapeuta terapeuta) throws Exception {
        log.info("Requesição para atualizar terapeuta pelo email: {}", email);
        return terapeutaService.atualizarInformacoes(terapeuta, email);
    }
}
