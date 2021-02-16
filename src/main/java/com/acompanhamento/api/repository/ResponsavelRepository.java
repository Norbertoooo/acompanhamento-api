package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Paciente;
import com.acompanhamento.api.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    Optional<Responsavel> findByNomeCompleto(String nome);
    Optional<Responsavel> findByLoginEmail(String email);
}
