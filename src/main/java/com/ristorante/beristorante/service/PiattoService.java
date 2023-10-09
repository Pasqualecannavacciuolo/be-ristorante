package com.ristorante.beristorante.service;

import com.ristorante.beristorante.domain.Menu;
import com.ristorante.beristorante.domain.Piatto;
import com.ristorante.beristorante.repository.MenuRepository;
import com.ristorante.beristorante.repository.PiattoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiattoService {
    
    @Autowired 
    PiattoRepository piattoRepository;
    @Autowired
    MenuRepository menuRepository;

    public Piatto findById(Integer id) {
        return piattoRepository.findById(id).orElse(null);
    }

    public List<Piatto> findAll() {
        return piattoRepository.findAll();
    }

    /*public List<Piatto> findByMenuId(Menu menu) {
        List<Piatto> listaPiatti = piattoRepository.findByMenuId(menu);
        Menu menu1 = menuRepository.findById(menu.getId()).orElse(null);
        for (Piatto piatto : listaPiatti) {
            if(piatto.getLista_menu() == menu1) {
                piatto.setChecked(true);
            }
        }
        return piattoRepository.findByMenuId(menu);
    }*/

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
