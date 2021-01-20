package com.acompanhamento.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Paciente implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private Integer idade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ficha", referencedColumnName = "id")
    private Ficha ficha = new Ficha();

    @ManyToOne
    @JoinColumn(name = "id_terapeuta", nullable = false)
    @JsonBackReference
    private Terapeuta terapeuta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.EAGER)
    private List<Responsavel> responsaveis;

}
