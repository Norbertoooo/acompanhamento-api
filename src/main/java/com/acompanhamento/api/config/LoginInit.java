package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.repository.LoginRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class LoginInit implements CommandLineRunner {

    private final LoginRepository loginRepository;

    private final PasswordEncoder bcryptEncoder;

    public LoginInit(LoginRepository loginRepository, PasswordEncoder bcryptEncoder) {
        this.loginRepository = loginRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public void run(String... args) {
        log.info("Inserindo usuario administrador de teste");
        Login login = new Login();
        login.setEmail("teste@gmail.com");
        login.setSenha(bcryptEncoder.encode("teste"));
        login.setPerfil(Perfil.ADMINISTRADOR);
        loginRepository.save(login);
    }
}
