package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.acompanhamento.api.shared.Constantes.Variaveis.SENHA_PADRAO_RESPONSAVEL;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl JavaMailSenderImpl;

    @Override
    public void enviarCredenciasResponsavel(Login login) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(login.getEmail());
        msg.setSubject("Rafa-Web - Credencias");
        msg.setText("Credencias para login: \nEmail: " + login.getEmail() + " \nSenha: " + SENHA_PADRAO_RESPONSAVEL);
        log.debug("Enviando email para: {}, com texto: \n {}", login.getEmail(), msg);
        JavaMailSenderImpl.send(msg);

    }
}
