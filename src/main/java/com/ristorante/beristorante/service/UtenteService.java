package com.ristorante.beristorante.service;

import com.ristorante.beristorante.repository.UtenteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ristorante.beristorante.domain.Utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    
    @Autowired 
    UtenteRepository utenteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Utente findById(Integer id) {
        return utenteRepository.findById(id).orElseThrow();
    }

    public Utente findUserByEmail(Object parametri) throws JsonProcessingException {
        // Crea un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte l'oggetto in formato JSON
        String jsonString = objectMapper.writeValueAsString(parametri);
        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode emailNode = node.get("email");
        String emailValue = emailNode.asText();
        return utenteRepository.findUserByEmail(emailValue).orElseThrow();
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

    public Utente updateCambioPassword(Object parametri, Integer id) throws JsonProcessingException {
        Utente utente = utenteRepository.getById(id);
        // Crea un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte l'oggetto in formato JSON
        String jsonString = objectMapper.writeValueAsString(parametri);
        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode newCambioPasswordNode = node.get("newCambioPassword");
        Boolean newCambioPasswordFlag = newCambioPasswordNode.asBoolean();
        JsonNode newPasswordNode = node.get("newPassword");
        String newPassword = passwordEncoder.encode(newPasswordNode.asText());
        System.out.println("JSON ---> "+newCambioPasswordFlag+newPassword);
        utente.setCambio_password(newCambioPasswordFlag);
        utente.setPassword(newPassword);
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
