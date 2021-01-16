package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.resource.dto.TerapeutaDTO;
import com.acompanhamento.api.service.TerapeutaService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/terapeutas/")
@CrossOrigin(origins = "*")
@Log4j2
public class TerapeutaResource {

    private final TerapeutaService terapeutaService;

    @Autowired
    private ModelMapper modelMapper;

    public TerapeutaResource(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @GetMapping("{email}")
    public TerapeutaDTO buscarTerapeutaPorEmail(@PathVariable String email) throws Exception {
        log.info("Requisição para buscar terapeuta pelo email de login: {}", email);
        return modelMapper.map(terapeutaService.buscarTerapeutaPorEmail(email), TerapeutaDTO.class);
    }

    @PutMapping("{email}")
    public Terapeuta atualizarTerapeuta(@PathVariable String email, @RequestBody Terapeuta terapeuta) throws Exception {
        log.info("Requesição para atualizar terapeuta pelo email: {}", email);
        return terapeutaService.atualizarInformacoes(terapeuta, email);
    }
}
