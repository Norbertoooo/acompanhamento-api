package com.acompanhamento.api.resource.dto;

import com.acompanhamento.api.domain.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Email
    private String email;

    @NotBlank
    @Length(min = 6, max = 12)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

}
