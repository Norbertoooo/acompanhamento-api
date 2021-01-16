package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
    Optional<Terapeuta> findByNomeCompleto(String nome);

    Optional<Terapeuta> findByLogin_Email(String email);

    Optional<Terapeuta> findByCrfa(Long crfa);

}
