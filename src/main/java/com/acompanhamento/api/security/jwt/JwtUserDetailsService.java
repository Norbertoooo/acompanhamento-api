package com.acompanhamento.api.security.jwt;

import com.acompanhamento.api.domain.Perfil;
import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Login> usuario = loginRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }
        List<GrantedAuthority> perfilAdmin = AuthorityUtils.createAuthorityList(Perfil.ADMINISTRADOR.name());
        List<GrantedAuthority> perfilResponsavel = AuthorityUtils.createAuthorityList(Perfil.RESPONSAVEL.name());
        List<GrantedAuthority> perfilTerapeuta = AuthorityUtils.createAuthorityList(Perfil.TERAPEUTA.name());

        if (usuario.get().getPerfil().equals(Perfil.ADMINISTRADOR)) {
            return new User(usuario.get().getEmail(), usuario.get().getSenha(), perfilAdmin);
        }
        if (usuario.get().getPerfil().equals(Perfil.RESPONSAVEL)) {
            return new User(usuario.get().getEmail(), usuario.get().getSenha(), perfilResponsavel);
        }
        return new User(usuario.get().getEmail(), usuario.get().getSenha(), perfilTerapeuta);
    }

    public Login save(Login user) {
        user.setSenha(bcryptEncoder.encode(user.getSenha()));
        return loginRepository.save(user);
    }

}
