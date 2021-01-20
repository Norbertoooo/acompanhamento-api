package com.acompanhamento.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 6, max = 12)
    private String senha;

}
