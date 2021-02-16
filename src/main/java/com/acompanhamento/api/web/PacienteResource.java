package com.acompanhamento.api.web;

import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.service.PacienteService;
import com.acompanhamento.api.web.dto.PacienteDTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
@Log4j2
public class PacienteResource {

    private final PacienteService pacienteService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/admin/{page}/{count}")
    public ResponseEntity<Page<Paciente>> listarPacientes(@PathVariable Integer page, @PathVariable Integer count) {
        log.info("Requisição para listar todos pacientes");
        return ResponseEntity.ok(pacienteService.listarPacientes(page, count));
    }

    @GetMapping("/{page}/{count}")
    public ResponseEntity<Page<Paciente>> listarPacientesPeloEmailDoTerapeuta(HttpServletRequest request,
                                                                              @PathVariable Integer page,
                                                                              @PathVariable Integer count) {
        String email = (jwtTokenUtil.getEmailLogado(request));
        log.info("Requisição para listar todos pacientes do terapeuta: {}", email);
        return ResponseEntity.ok(pacienteService.listarPacientesPeloEmailDoTerapeuta(email, page, count));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Paciente> buscarPacientePeloNome(@PathVariable String nome, HttpServletRequest request) throws Exception {
        String email = (jwtTokenUtil.getEmailLogado(request));
        log.info("Requisição para listar paciente de nome: {}", nome);
        return ResponseEntity.ok(pacienteService.buscarPacientePeloNome(nome, email));
    }

    @PostMapping
    public ResponseEntity<Paciente> adicionarPaciente(@RequestBody Paciente paciente, HttpServletRequest request) throws Exception {
        String email = (jwtTokenUtil.getEmailLogado(request));
        log.info("Requisição para salvar paciente {} para o terapeuta do email {}", paciente, email);
        return ResponseEntity.ok(pacienteService.cadastrarPaciente(email, paciente));
    }

    @DeleteMapping
    public ResponseEntity<Void> removerPacientesPeloId(HttpServletRequest request, @RequestBody List<PacienteDTO> pacientes) {
        log.info("Requisição para remover paciente para o terapeuta do email");
        String emailLogado = (jwtTokenUtil.getEmailLogado(request));
        List<Long> pacientesId = new ArrayList<>();
        pacientes.forEach(pacienteDTO -> pacientesId.add(pacienteDTO.getId()));
        pacienteService.removerPacientesPeloId(pacientesId, emailLogado);
        return ResponseEntity.noContent().build();
    }

}
