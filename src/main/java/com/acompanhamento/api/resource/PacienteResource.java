package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.resource.dto.PacienteDTO;
import com.acompanhamento.api.service.PacienteService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pacientes/")
@CrossOrigin(origins = "*")
@Log4j2
public class PacienteResource {

    private final PacienteService pacienteService;

    @Autowired
    private ModelMapper modelMapper;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("{email}")
    public ResponseEntity<List<PacienteDTO>> listarPacientesPeloEmailDoTerapeuta(@PathVariable String email) {
        List<PacienteDTO> pacienteDTOS = pacienteService.listarPacientesPeloEmailDoTerapeuta(email).stream().map(
                paciente -> modelMapper.map(paciente, PacienteDTO.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(pacienteDTOS);
    }

    @GetMapping("{nome}/{email}")
    public ResponseEntity<Paciente> buscarPacientePeloNome(@PathVariable String nome, @PathVariable String email) throws Exception {
        return ResponseEntity.ok(pacienteService.buscarPacientePeloNome(nome, email));
    }

    @PostMapping("{email}")
    public ResponseEntity<Paciente> adicionarPaciente(@PathVariable String email, @RequestBody Paciente paciente) throws Exception {
        log.info("Requisição para salvar paciente {} para o terapeuta do email {}", paciente, email);
        return ResponseEntity.ok(pacienteService.cadastrarPaciente(email, paciente));
    }

    @DeleteMapping("{nome}/{email}")
    public ResponseEntity<Void> removerPacientePeloNome(@PathVariable String nome, @PathVariable String email) {
        log.info("Requisição para remover paciente {} para o terapeuta do email {}", nome, email);
        pacienteService.removerPacientePeloNome(nome, email);
        return ResponseEntity.noContent().build();
    }

}
