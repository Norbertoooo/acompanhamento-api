package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.service.LoginService;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.service.TerapeutaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.acompanhamento.api.domain.Perfil.*;

@Configuration
@Log4j2
public class LoginInit implements CommandLineRunner {

    private final LoginService loginService;

    private final TerapeutaService terapeutaService;

    private final ResponsavelService responsavelService;

    private final PasswordEncoder bcryptEncoder;

    public LoginInit(LoginService loginService, TerapeutaService terapeutaService, ResponsavelService responsavelService, PasswordEncoder bcryptEncoder) {
        this.loginService = loginService;
        this.terapeutaService = terapeutaService;
        this.responsavelService = responsavelService;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public void run(String... args) {
        log.info("Inserindo usuário administrador");
        loginService.save(new Login("admin@gmail.com", bcryptEncoder.encode("teste123"), ADMINISTRADOR));

        log.info("Inserindo usuário Terapeuta");
        terapeutaService.cadastrarLoginTerapeuta(
                new Login("terapeuta@gmail.com", bcryptEncoder.encode("teste123"), TERAPEUTA));

        log.info("Inserindo usuário Responsavel");
        responsavelService.cadastrarLoginResponsavel(
                new Login("responsavel@gmail.com", bcryptEncoder.encode("teste123"), RESPONSAVEL));
    }
}
