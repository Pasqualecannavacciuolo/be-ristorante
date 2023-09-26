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

    public Utente findById(Integer id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email).orElse(null);
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente saveOne(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Utente changeOne(Utente newUtente, Integer id) {
        return utenteRepository.findById(id)
        .map(utente -> {
            utente.setCambio_password(newUtente.getCambio_password());
            utente.setCognome(newUtente.getCognome());
            utente.setCreato_da(newUtente.getCreato_da());
            utente.setCreato_il(newUtente.getCreato_il());
            utente.setEmail(newUtente.getEmail());
            utente.setPassword(newUtente.getPassword());
            //utente.setId(newUtente.getId());
            utente.setModificato_da(newUtente.getModificato_da());
            utente.setModificato_il(newUtente.getModificato_il());
            utente.setNome(newUtente.getNome());
            utente.setNome_utente(newUtente.getNome_utente());
            utente.setSalt(newUtente.getSalt());
            utente.setUltima_modifica_password(newUtente.getUltima_modifica_password());
            utente.setUltimo_accesso(newUtente.getUltimo_accesso());
            return utenteRepository.save(utente);
        })
        .orElseGet(() -> {
            newUtente.setId(id);
            return utenteRepository.save(newUtente);
          });
        //return utenteRepository.save(newUtente);
        
    }

    public void deleteOne(Integer id) {
        utenteRepository.deleteById(id);
    }
}
