package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Usuario;
import com.acompanhamento.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioRepository.findByEmail(email).get());
    }
}
