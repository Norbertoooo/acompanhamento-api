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
    private Long id;

    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private Long telefone;

    private Long crfa;

    private String cpf;

    private String especialidade;

    private String formacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco = new Endereco();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "email", nullable = false)
    private Login login;

    @OneToMany(mappedBy = "terapeuta", orphanRemoval = true)
    @JsonIgnore
    private List<Paciente> pacientes;

}
