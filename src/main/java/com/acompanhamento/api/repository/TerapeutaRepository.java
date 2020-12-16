package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
}
