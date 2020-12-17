package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Responsavel;
import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.repository.ResponsavelRepository;
import com.acompanhamento.api.repository.TerapeutaRepository;
import com.acompanhamento.api.resource.dto.LoginDTO;
import com.acompanhamento.api.resource.dto.RespostaAutenticacaoDTO;
import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.security.jwt.JwtUserDetailsService;
import com.acompanhamento.api.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.acompanhamento.api.domain.Perfil.RESPONSAVEL;
import static com.acompanhamento.api.domain.Perfil.TERAPEUTA;
import static com.acompanhamento.api.shared.Constantes.Mensagens.CADASTRO_SUCESSO;
import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.FALHA_AO_CADASTRAR;
import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.LOGIN_JA_EXISTENTE;

@RestController
@CrossOrigin(origins = "*")
@Log4j2
public class LoginResource {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final LoginService loginService;

    private final TerapeutaRepository terapeutaRepository;

    private final ResponsavelRepository responsavelRepository;

    private final ModelMapper modelMapper;

    public LoginResource(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, LoginService loginService, TerapeutaRepository terapeutaRepository, ResponsavelRepository responsavelRepository, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.loginService = loginService;
        this.terapeutaRepository = terapeutaRepository;
        this.responsavelRepository = responsavelRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/autenticar")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new RespostaAutenticacaoDTO(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarNovoUsuario(@Valid @RequestBody LoginDTO loginDTO) {
        Login login = modelMapper.map(loginDTO, Login.class);
        if (loginService.loginExistente(login.getEmail())) {
            return ResponseEntity.badRequest().body(LOGIN_JA_EXISTENTE);
        }
        log.info("Requisição para cadastrar novo usuário: {}", login);
        if (login.getPerfil().equals(TERAPEUTA)) {
            Terapeuta terapeuta = new Terapeuta();
            loginService.save(login);
            terapeuta.setLogin(login);
            terapeutaRepository.save(terapeuta);
            return ResponseEntity.ok(CADASTRO_SUCESSO);
            // TODO: 17/12/2020 virar metodo de um service
        }
        if (login.getPerfil().equals(RESPONSAVEL)) {
            Responsavel responsavel = new Responsavel();
            loginService.save(login);
            responsavel.setLogin(login);
            responsavelRepository.save(responsavel);
            return ResponseEntity.ok(CADASTRO_SUCESSO);
        }
        return ResponseEntity.badRequest().body(FALHA_AO_CADASTRAR);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Usuario Desabilitado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais Inválidas", e);
        }
    }
}
