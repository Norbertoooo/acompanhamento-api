package com.acompanhamento.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cep;

    private Long numero;

    private String rua;

    private String bairro;

    private String detalhes;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco")
    private Terapeuta terapeuta;

}
