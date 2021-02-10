package com.acompanhamento.api.web;

import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.web.dto.ResponsavelDTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/responsaveis")
@CrossOrigin(origins = "*")
@Log4j2
public class ResponsavelResource {

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponsavelDTO buscarResponsavelPorEmail(HttpServletRequest request) {
        String email = jwtTokenUtil.getEmailLogado(request);
        log.info("Requisição para buscar responsável pelo email de login: {}", email);
        return modelMapper.map(responsavelService.buscarResponsavelPeloEmail(email), ResponsavelDTO.class);
    }
}
