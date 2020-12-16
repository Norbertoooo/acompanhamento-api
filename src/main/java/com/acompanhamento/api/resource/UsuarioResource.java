package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioResource {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<Login>> listarTodosUsuarios() {
        return ResponseEntity.ok(loginRepository.findAll());
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Login> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(loginRepository.findByEmail(email).get());
    }
}
