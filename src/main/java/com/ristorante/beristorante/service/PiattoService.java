package com.ristorante.beristorante.service;

import com.ristorante.beristorante.domain.Piatto;
import com.ristorante.beristorante.repository.PiattoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiattoService {
    
    @Autowired 
    PiattoRepository piattoRepository;

    public Piatto findById(Integer id) {
        return piattoRepository.findById(id).orElse(null);
    }

    public List<Piatto> findAll() {
        return piattoRepository.findAll();
    }

    public List<Piatto> findByMenuId(Integer id) {
        return piattoRepository.findByMenuId(id);
    }

    public Piatto saveOne(Piatto Piatto) {
        return piattoRepository.save(Piatto);
    }

    public Piatto changeOne(Piatto newPiatto, Integer id) {
        return piattoRepository.findById(id)
        .map(Piatto -> {
            Piatto.setNome(newPiatto.getNome());
            Piatto.setCosto(newPiatto.getCosto());
            Piatto.setDescrizione(newPiatto.getDescrizione());
            return piattoRepository.save(Piatto);
        })
        .orElseGet(() -> {
            newPiatto.setId(id);
            return piattoRepository.save(newPiatto);
          });        
    }

    public void deleteOne(Integer id) {
        piattoRepository.deleteById(id);
    }
}
