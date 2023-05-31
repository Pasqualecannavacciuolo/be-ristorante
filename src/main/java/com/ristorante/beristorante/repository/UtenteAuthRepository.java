package com.ristorante.beristorante.repository;
import com.ristorante.beristorante.domain.Utente_Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteAuthRepository extends JpaRepository<Utente_Auth, Integer>{
    Optional<Utente_Auth> findByEmail(String email);
}
