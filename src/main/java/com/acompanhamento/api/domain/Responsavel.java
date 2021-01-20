package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private Integer idade;

    private Long telefone;

    private String cpf;

    private String parentesco;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "id_login", referencedColumnName = "email")
    private Login login;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    public Responsavel(Login login) {
        this.login = login;
    }

}
