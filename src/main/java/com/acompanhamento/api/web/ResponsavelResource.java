package com.acompanhamento.api.web;

import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.domain.Responsavel;
import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.service.PacienteService;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.web.dto.ResponsavelDTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
@CrossOrigin(origins = "*")
@Log4j2
public class ResponsavelResource {

    private final ResponsavelService responsavelService;

    private final PacienteService pacienteService;

    private final ModelMapper modelMapper;

    private final JwtTokenUtil jwtTokenUtil;

    public ResponsavelResource(ResponsavelService responsavelService, PacienteService pacienteService, ModelMapper modelMapper, JwtTokenUtil jwtTokenUtil) {
        this.responsavelService = responsavelService;
        this.pacienteService = pacienteService;
        this.modelMapper = modelMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/{page}/{count}")
    public Page<Responsavel> listarResponsaveis(HttpServletRequest request, @PathVariable Integer page, @PathVariable Integer count) {
        log.info("Requisição para listar responsáveis");
        return responsavelService.listarTodosResponsaveis(page, count);
    }

    @GetMapping
    public ResponsavelDTO buscarResponsavelPorEmail(HttpServletRequest request) {
        String email = jwtTokenUtil.getEmailLogado(request);
        log.info("Requisição para buscar responsável pelo email de login: {}", email);
        return modelMapper.map(responsavelService.buscarResponsavelPeloEmail(email), ResponsavelDTO.class);
    }

    @GetMapping("/pacientes")
    public List<Paciente> listarPacientesPorEmail(HttpServletRequest request) {
        String email = jwtTokenUtil.getEmailLogado(request);
        log.info("Requisição para listar protegidos do responsável pelo email de login: {}", email);
        return pacienteService.listarPacientesPeloEmailDoResponsavel(email);
    }
}
