package com.acompanhamento.api.resource;

import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.resource.dto.CadastroTerapeutaDTO;
import com.acompanhamento.api.resource.dto.LoginDTO;
import com.acompanhamento.api.resource.dto.RespostaAutenticacaoDTO;
import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.security.jwt.JwtUserDetailsService;
import com.acompanhamento.api.service.LoginService;
import com.acompanhamento.api.service.ResponsavelService;
import com.acompanhamento.api.service.TerapeutaService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.acompanhamento.api.domain.Perfil.RESPONSAVEL;
import static com.acompanhamento.api.domain.Perfil.TERAPEUTA;
import static com.acompanhamento.api.shared.Constantes.Mensagens.CADASTRO_SUCESSO;
import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.FALHA_AO_CADASTRAR;
import static com.acompanhamento.api.shared.Constantes.MensagensDeErro.LOGIN_JA_EXISTENTE;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
@Log4j2
public class LoginResource {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final LoginService loginService;

    private final ResponsavelService responsavelService;

    private final TerapeutaService terapeutaService;

    private final ModelMapper modelMapper;

    public LoginResource(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, LoginService loginService, ResponsavelService responsavelService, TerapeutaService terapeutaService, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.loginService = loginService;
        this.responsavelService = responsavelService;
        this.terapeutaService = terapeutaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new RespostaAutenticacaoDTO(authenticationRequest,token));
    }

    @PostMapping("cadastrar")
    public ResponseEntity<?> cadastrarTerapeuta(@Valid @RequestBody CadastroTerapeutaDTO cadastroTerapeutaDTO) throws Exception {
        log.info("Requisição para cadastrar novo terapeuta: {}", cadastroTerapeutaDTO);
        Login login = modelMapper.map(cadastroTerapeutaDTO.getLogin(), Login.class);
        Terapeuta terapeuta = modelMapper.map(cadastroTerapeutaDTO, Terapeuta.class);
        return ResponseEntity.ok(terapeutaService.cadastrarLoginTerapeuta(terapeuta));
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
