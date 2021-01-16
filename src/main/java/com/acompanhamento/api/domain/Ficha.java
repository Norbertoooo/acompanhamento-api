package com.acompanhamento.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ficha implements Serializable {

    private static final long serialVersionUID = 42L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observacoes;

    private Long nivel;

    private String sensibilidade;

    private Long duracao;

    private Long pontuacao;

    @OneToOne(mappedBy = "ficha")
    @JsonBackReference
    private Paciente paciente;

    @Override
    public String toString() {
        return "Ficha{" +
                "id=" + id +
                ", observacoes='" + observacoes + '\'' +
                ", nivel=" + nivel +
                ", sensibilidade='" + sensibilidade + '\'' +
                ", duracao=" + duracao +
                ", pontuacao=" + pontuacao +
                ", paciente=" + paciente +
                '}';
    }
}
