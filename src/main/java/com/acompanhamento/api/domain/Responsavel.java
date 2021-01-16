package com.acompanhamento.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private Paciente pacientes;

    public Responsavel(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", idade=" + idade +
                ", telefone=" + telefone +
                ", cpf='" + cpf + '\'' +
                ", parentesco='" + parentesco + '\'' +
                ", endereco=" + endereco +
                ", login=" + login +
                ", pacientes=" + pacientes +
                '}';
    }
}
