package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Ficha;
import com.acompanhamento.api.repository.FichaRepository;
import com.acompanhamento.api.resource.dto.FichaDTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fichas/")
@CrossOrigin(origins = "*")
@Log4j2
public class FichaResource {

    @Autowired
    private FichaRepository fichaRepository;
    // TODO: 24/12/2020 transformar em service

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("{nome}")
    public ResponseEntity<FichaDTO> listarPacientesPeloEmailDoTerapeuta(@PathVariable String nome) {
        log.info("Requisição para listar ficha do paciente: {}", nome);
        Ficha ficha = fichaRepository.findByPaciente_NomeCompleto(nome);
        return ResponseEntity.ok(modelMapper.map(ficha, FichaDTO.class));
    }
}
