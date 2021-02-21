package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Log4j2
@Profile( {"test", "dev"} )
public class AdminInit implements CommandLineRunner {

    private final LoginService loginService;

    public AdminInit(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (loginService.findByEmail("admin@gmail.com").isEmpty()) {
            log.info("Inserindo admin");
            this.loginService.save(new Login("admin@gmail.com", "adminadmin", Perfil.ADMINISTRADOR));
        }
    }
}
