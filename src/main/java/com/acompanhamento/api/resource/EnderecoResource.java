package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Endereco;
import com.acompanhamento.api.service.EnderecoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enderecos/")
@CrossOrigin(origins = "*")
@Log4j2
public class EnderecoResource {

    private final EnderecoService enderecoService;

    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoPeloId(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Endereco> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoPeloCpf(cpf));
    }
}
