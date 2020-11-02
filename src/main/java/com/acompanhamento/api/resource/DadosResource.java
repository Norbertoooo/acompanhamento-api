package com.acompanhamento.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dados")
@CrossOrigin(origins = "*")
public class DadosResource {

    private String frase;

    @PostMapping
    public ResponseEntity<String> coletarDados(@RequestBody String frase) {
        this.frase = frase;
        return ResponseEntity.ok("frase: " + frase);
    }

    @GetMapping
    public ResponseEntity<String> exibirDados() {
        return ResponseEntity.ok(frase);
    }
}
