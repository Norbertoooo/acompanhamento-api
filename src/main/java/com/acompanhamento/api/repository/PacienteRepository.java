package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findAllByTerapeuta_NomeCompleto(String terapeuta_nomeCompleto);
    List<Paciente> findAllByTerapeuta_Login_Email(String email);
    Optional<Paciente> findByNomeCompletoAndTerapeuta_Login_Email(String nomeCompleto, String email);
    void deleteByNomeCompletoAndTerapeuta_Login_Email(String nomeCompleto, String terapeuta_login_email);
}
