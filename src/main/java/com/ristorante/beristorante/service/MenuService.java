package com.ristorante.beristorante.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.json.JSONArray;
import org.json.JSONObject;
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

    @Autowired
    PiattoService piattoService;

    public Menu findById(Integer id) {
        return menuRepository.findById(id).orElse(null);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Menu saveOne(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu addPiatti(Menu menu_where_to_add, Object lista_piatti) throws JsonProcessingException {
        // Crea un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte l'oggetto in formato JSON
        String jsonString = objectMapper.writeValueAsString(lista_piatti);
        JSONArray jsonArr = new JSONArray(jsonString);
        
        // Popolo la Lista dei Piatti con i piatti provenienti dal Frontend contenuti in jsonArr
        List<Piatto> piattiFromFrontend =  new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            Piatto piatto = new Piatto();
            piatto.setId((Integer) jsonObj.get("id"));
            piatto.setNome(jsonObj.getString("nome"));
            piatto.setCosto((Integer) jsonObj.get("costo"));
            piatto.setDescrizione(jsonObj.getString("descrizione"));
            piattiFromFrontend.add(piatto);
        }

        // Aggiorno la lista dei piatti per questo menu specifico
        menu_where_to_add.setLista_piatti(piattiFromFrontend);
        saveOne(menu_where_to_add);

        return menu_where_to_add;
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


    public void deleteOne(Integer id) {
        menuRepository.deleteById(id);
    }

}
