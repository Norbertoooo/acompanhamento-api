package com.acompanhamento.api.web;

import com.acompanhamento.api.domain.Endereco;
import com.acompanhamento.api.domain.Login;
import com.acompanhamento.api.domain.Terapeuta;
import com.acompanhamento.api.security.jwt.JwtAuthenticationEntryPoint;
import com.acompanhamento.api.security.jwt.JwtTokenUtil;
import com.acompanhamento.api.security.jwt.JwtUserDetailsService;
import com.acompanhamento.api.service.EnderecoService;
import com.acompanhamento.api.service.LoginService;
import com.acompanhamento.api.service.PacienteService;
import com.acompanhamento.api.service.TerapeutaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static com.acompanhamento.api.domain.Perfil.TERAPEUTA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(controllers = LoginResource.class)
@Log4j2
public class LoginResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoginService loginService;

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TerapeutaService terapeutaService;

    @MockBean
    private PacienteService pacienteService;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void deveRetornarSucessoAoCadastrarTerapeuta() throws Exception {
        Terapeuta terapeuta = new Terapeuta();
        terapeuta.setNomeCompleto("teste");
        terapeuta.setCrfa(12323232L);
        terapeuta.setFormacao("dasdsadasd");
        terapeuta.setDataNascimento(new Date());
        terapeuta.setCpf("43243234324");
        terapeuta.setTelefone(43432432L);
        terapeuta.setEndereco(new Endereco());
        terapeuta.setEspecialidade("dsadasdas");
        terapeuta.setLogin(new Login("teste@gmail.com", "123456", TERAPEUTA));
        when(terapeutaService.cadastrarTerapeuta(terapeuta)).thenReturn(eq(terapeuta), any(Terapeuta.class));

        mockMvc.perform(post("/api/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(terapeuta)))
                .andExpect(status().is2xxSuccessful());
    }

    private String tojson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
    }
}
