package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.repository.LoginRepository;
import com.acompanhamento.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.LOGIN_JA_EXISTENTE;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Login save(Login login) {
        login.setSenha(bcryptEncoder.encode(login.getSenha()));
        return loginRepository.save(login);
    }

    @Override
    public Optional<Login> findByEmail(String email) throws Exception {
        return loginRepository.findByEmail(email);
    }

    @Override
    public void loginExistente(String email) throws Exception {
        loginRepository.findByEmail(email).orElseThrow( () -> new Exception(LOGIN_JA_EXISTENTE));
    }
}
