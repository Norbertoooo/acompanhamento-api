package com.acompanhamento.api.web;

import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.web.dto.TerapeutaDTO;
import com.acompanhamento.api.service.TerapeutaService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/terapeutas")
@CrossOrigin(origins = "*")
@Log4j2
public class TerapeutaResource {

    private final TerapeutaService terapeutaService;

    private final ModelMapper modelMapper;

    private final JwtTokenUtil jwtTokenUtil;

    public TerapeutaResource(TerapeutaService terapeutaService, ModelMapper modelMapper, JwtTokenUtil jwtTokenUtil) {
        this.terapeutaService = terapeutaService;
        this.modelMapper = modelMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public TerapeutaDTO buscarTerapeutaPorEmail(HttpServletRequest request) throws Exception {
        String email = (jwtTokenUtil.getEmailLogado(request));
        log.info("Requisição para buscar terapeuta pelo email de login: {}", email);
        return modelMapper.map(terapeutaService.buscarTerapeutaPorEmail(email), TerapeutaDTO.class);
    }

    @PutMapping
    public ResponseEntity<Terapeuta> atualizarTerapeuta(HttpServletRequest request, @RequestBody TerapeutaDTO terapeutaDTO) throws Exception {
        String email = (jwtTokenUtil.getEmailLogado(request));
        log.info("Requesição para atualizar terapeuta pelo email: {}", email);
        Terapeuta terapeuta = terapeutaService.atualizarInformacoes(modelMapper.map(terapeutaDTO, Terapeuta.class), email);
        return ResponseEntity.ok(terapeuta);
    }

    @GetMapping("/{page}/{count}")
    public ResponseEntity<Page<Terapeuta>> listarTerapeutas(@PathVariable Integer page, @PathVariable Integer count) {
        log.info("Requisição para listar todos terapeutas");
        return ResponseEntity.ok(terapeutaService.listarTerapeutas(page, count));
    }
}
