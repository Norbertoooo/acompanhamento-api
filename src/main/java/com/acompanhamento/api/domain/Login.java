package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login")
public class Login implements Serializable {

    private static final long serialVersionUID = 42L;


    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "perfil")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

}
