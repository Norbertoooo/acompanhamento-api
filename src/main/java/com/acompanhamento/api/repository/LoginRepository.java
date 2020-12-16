package com.acompanhamento.api.repository;

import com.acompanhamento.api.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    Optional<Login> findByEmail(String email);
}
