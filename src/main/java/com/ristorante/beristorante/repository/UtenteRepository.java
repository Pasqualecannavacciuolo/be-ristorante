package com.ristorante.beristorante.repository;
import com.ristorante.beristorante.domain.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{
    
}