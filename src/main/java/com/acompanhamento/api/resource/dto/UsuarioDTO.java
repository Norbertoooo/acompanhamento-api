package com.acompanhamento.api.resource.dto;

import com.acompanhamento.api.domain.Papel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UsuarioDTO {

    @Email
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    private Papel papel;
    private String token;

}
