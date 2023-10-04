package com.ristorante.beristorante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ristorante.beristorante.domain.Menu;
import com.ristorante.beristorante.domain.Piatto;
import com.ristorante.beristorante.repository.MenuRepository;
import com.ristorante.beristorante.repository.PiattoRepository;

@Service
public class MenuService {
    @Autowired 
    MenuRepository menuRepository;

    @Autowired
    PiattoRepository piattoRepository;

    public Menu findById(Integer id) {
        return menuRepository.findById(id).orElse(null);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Menu saveOne(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu changeMenu(Object parametri, Integer id) throws JsonProcessingException {
        System.out.println("JSON ---> "+parametri);
        Menu menu = menuRepository.getById(id);
        // Crea un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte l'oggetto in formato JSON
        String jsonString = objectMapper.writeValueAsString(parametri);
        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode newNomeMenuNode = node.get("newNomeMenu");
        String nomeMenu = newNomeMenuNode.asText();
        JsonNode newAttivoNode = node.get("newAttivo");
        Boolean Attivo = newAttivoNode.asBoolean();
        System.out.println("JSON ---> "+nomeMenu+Attivo);
        menu.setNome(nomeMenu);
        menu.setAttivo(Attivo);
        return menuRepository.save(menu);    
    }

    /*public Menu changeOne(Object parametri, Integer id) throws JsonProcessingException{
        Menu menu = menuRepository.getById(id);
        // Crea un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte l'oggetto in formato JSON
        String jsonString = objectMapper.writeValueAsString(parametri);
        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode newPiattoIdNode = node.get("piatto_id");
        Integer PiattoId = newPiattoIdNode.asInt();
        JsonNode newDisponibileNode = node.get("disponibile");
        Boolean Disponibile = newDisponibileNode.asBoolean();
        System.out.println("JSON ---> "+PiattoId+Disponibile);
        Piatto newPiatto = piattoRepository.findById(PiattoId).orElse(null);
        menu.setPiatto(newPiatto);
        menu.setDisponibile(Disponibile);
        return menuRepository.save(menu);       
    }*/

    public void deleteOne(Integer id) {
        menuRepository.deleteById(id);
    }
}
