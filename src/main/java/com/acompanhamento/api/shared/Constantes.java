package com.acompanhamento.api.shared;

public class Constantes {

    public interface Variaveis {

    }

    public interface Mensagens {
        String CADASTRO_SUCESSO = "Cadastro realizado com sucesso";
    }

    public interface MensagensDeErro {
        String FALHA_AO_CADASTRAR = "Falha ao cadastrar novo usuario";
        String LOGIN_JA_EXISTENTE = "Email já cadastrado";
    }
}
