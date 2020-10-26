package com.acompanhamento.api.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Usuario {

    @Id
    @Column(unique = true)
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Papel papel;
    private String token;

}
