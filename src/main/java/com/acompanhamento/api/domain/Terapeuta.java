package com.acompanhamento.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Terapeuta implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "crfa")
    private Long crfa;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "formacao")
    private String formacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "email", nullable = false)
    private Login login;

    @OneToMany(mappedBy = "terapeuta")
    @JsonIgnore
    private List<Paciente> pacientes;

}
