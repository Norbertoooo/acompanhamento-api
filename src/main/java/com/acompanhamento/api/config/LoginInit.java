package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.service.EmailService;
import com.acompanhamento.api.service.LoginService;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.service.TerapeutaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.acompanhamento.api.domain.Perfil.*;

@Configuration
@Log4j2
@Profile("not")
public class LoginInit implements CommandLineRunner {

    private final LoginService loginService;

    private final TerapeutaService terapeutaService;

    private final ResponsavelService responsavelService;

    private final PasswordEncoder bcryptEncoder;

    @Autowired
    private EmailService emailService;

    public LoginInit(LoginService loginService, TerapeutaService terapeutaService, ResponsavelService responsavelService, PasswordEncoder bcryptEncoder) {
        this.loginService = loginService;
        this.terapeutaService = terapeutaService;
        this.responsavelService = responsavelService;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public void run(String... args) {
        log.info("Inserindo usuário administrador");
        // log.info("enviando email de teste");

        // emailService.enviar("teste de envio", "vithor.norberto@gmail.com");

        loginService.save(new Login("admin@gmail.com", bcryptEncoder.encode("teste123"), ADMINISTRADOR));

        /*
        log.info("Inserindo usuário Terapeuta");
        terapeutaService.cadastrarLoginTerapeuta(
                new Login("terapeuta1@gmail.com", bcryptEncoder.encode("teste123"), TERAPEUTA));

        log.info("Inserindo usuário Terapeuta");
        terapeutaService.cadastrarLoginTerapeuta(
                new Login("terapeuta2@gmail.com", bcryptEncoder.encode("teste123"), TERAPEUTA));

        log.info("Inserindo usuário Terapeuta");
        terapeutaService.cadastrarLoginTerapeuta(
                new Login("terapeuta3@gmail.com", bcryptEncoder.encode("teste123"), TERAPEUTA));

        log.info("Inserindo usuário Terapeuta");
        terapeutaService.cadastrarLoginTerapeuta(
                new Login("terapeuta4@gmail.com", bcryptEncoder.encode("teste123"), TERAPEUTA));

        log.info("Inserindo usuário Terapeuta");
        terapeutaService.cadastrarLoginTerapeuta(
                new Login("terapeuta5@gmail.com", bcryptEncoder.encode("teste123"), TERAPEUTA));


        log.info("Inserindo usuário Responsavel");
        responsavelService.cadastrarLoginResponsavel(
                new Login("responsavel@gmail.com", bcryptEncoder.encode("teste123"), RESPONSAVEL));

         */
    }
}
