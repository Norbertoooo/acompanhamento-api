package com.acompanhamento.api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Terapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private Integer idade;

    private Long telefone;

    private Long crp;

    private String cpf;

    private String especialidade;

    private String formacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "email")
    private Login login;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "terapeuta", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Paciente> pacientes;

    public Terapeuta(Login login) {
        this.login = login;
    }

}
