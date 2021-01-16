package com.acompanhamento.api.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exception {

    private String mensagem;
    private OffsetDateTime data = OffsetDateTime.now();
    private String url;
    private Long status;

}
