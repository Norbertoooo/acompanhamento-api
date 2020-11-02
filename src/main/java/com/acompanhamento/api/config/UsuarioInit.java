package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.domain.Usuario;
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
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setSenha(bcryptEncoder.encode("testedesenha123"));
        usuario.setPerfil(Perfil.ADMINISTRADOR);
        usuarioRepository.save(usuario);
    }
}
