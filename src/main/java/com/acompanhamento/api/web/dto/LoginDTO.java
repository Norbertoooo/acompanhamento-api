package com.acompanhamento.api.web.dto;

import com.acompanhamento.api.domain.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    @Length(min = 6, max = 12)
    private String senha;

    private Perfil perfil;

    public LoginDTO(@Email @NotBlank @NotNull String email, Perfil perfil) {
        this.email = email;
        this.perfil = perfil;
    }
}
