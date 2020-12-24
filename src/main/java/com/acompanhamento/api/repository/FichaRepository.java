package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
    Ficha findByPaciente_NomeCompleto(String nomeCompleto);
}
