package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class UsuarioInit implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder bcryptEncoder;

    public UsuarioInit(UsuarioRepository usuarioRepository, PasswordEncoder bcryptEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public void run(String... args) {
        log.info("Inserindo usuario administrador de teste");
        Login login = new Login();
        login.setEmail("teste@gmail.com");
        login.setSenha(bcryptEncoder.encode("teste"));
        login.setPerfil(Perfil.ADMINISTRADOR);
        usuarioRepository.save(login);
    }
}
