package com.ristorante.beristorante.service;

import com.ristorante.beristorante.repository.UtenteRepository;
import com.ristorante.beristorante.domain.Utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    
    @Autowired 
    UtenteRepository utenteRepository;

    public Optional<Utente> findById(Integer id) {
        return utenteRepository.findById(id);
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente saveOne(Utente consegna) {
        return utenteRepository.save(consegna);
    }

    public void deleteOne(Integer id) {
        utenteRepository.deleteById(id);
    }
}
