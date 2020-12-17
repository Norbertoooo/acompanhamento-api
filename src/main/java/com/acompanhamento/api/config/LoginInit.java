package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.acompanhamento.api.domain.Perfil.ADMINISTRADOR;

@Configuration
@Log4j2
public class LoginInit implements CommandLineRunner {

    private final LoginService loginService;

    private final PasswordEncoder bcryptEncoder;

    public LoginInit(LoginService loginService, PasswordEncoder bcryptEncoder) {
        this.loginService = loginService;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public void run(String... args) {
        log.info("Inserindo usuário administrador");
        loginService.save(new Login("teste@gmail.com",bcryptEncoder.encode("teste"),ADMINISTRADOR));
    }
}
