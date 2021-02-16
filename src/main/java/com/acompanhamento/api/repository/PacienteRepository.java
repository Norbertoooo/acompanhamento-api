package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.acompanhamento.api.shared.Constantes.Queries.LISTAR_PACIENTES_RESPONSAVEL;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findAllByTerapeuta_NomeCompleto(String terapeuta_nomeCompleto);

    Page<Paciente> findAllByTerapeuta_Login_Email(String email, Pageable page);

    Optional<Paciente> findByNomeCompletoAndTerapeuta_Login_Email(String nomeCompleto, String email);

    @Query(value = LISTAR_PACIENTES_RESPONSAVEL, nativeQuery = true)
    List<Paciente> findAllByResponsavel_Login_Email(String email);

    @Transactional
    void deleteByNomeCompletoAndTerapeuta_Login_Email(String nomeCompleto, String terapeuta_login_email);
}
