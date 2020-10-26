package com.acompanhamento.api.config;

import com.acompanhamento.api.domain.Papel;
import com.acompanhamento.api.domain.Usuario;
import com.acompanhamento.api.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class UsuarioInit implements CommandLineRunner {


    private final UsuarioRepository usuarioRepository;

    public UsuarioInit(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        log.info("Inserindo usuario administrador de teste");
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setSenha("testedesenha123");
        usuario.setPapel(Papel.ADMINISTRADOR);
        usuario.setToken("algumtokenestranho");
        usuarioRepository.save(usuario);
    }
}
