package com.ristorante.beristorante.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ristorante.beristorante.BeRistoranteApplication;
import com.ristorante.beristorante.domain.Utente;
import com.ristorante.beristorante.service.UtenteService;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ristorante/management/utenti")
@PreAuthorize("hasRole('ADMIN')")
public class UtenteController {
    private static final Logger logger = LoggerFactory.getLogger(BeRistoranteApplication.class);
    private static ResponseEntity<?> result = new ResponseEntity<>(HttpStatus.LOCKED);

    @Autowired
    UtenteService utenteService;
    
    
    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<?> findAll() {
        result = new ResponseEntity<>(utenteService.findAll(), HttpStatus.OK);
        if(result.getStatusCode() == HttpStatus.OK) {
            logger.info("UTENTI - findAll - Status: 200");
            return new ResponseEntity<>(utenteService.findAll(), HttpStatus.OK);
        } 
        logger.info("UTENTI - findAll - Status: "+result.getStatusCode().toString());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<Utente> findById(@PathVariable Integer id) {
        try {
            result = new ResponseEntity<>(utenteService.findById(id), HttpStatus.OK);
            logger.info("UTENTI - findByID - Status: 200");
            Utente utente = utenteService.findById(id);
            return new ResponseEntity<Utente>(utente, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Message: "+e.getMessage()+" - Value: "+e.getCause());
            logger.error("UTENTI - findByID - Status: "+HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(path = "/byEmail")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<Utente> findByEmail(@RequestBody String email) {
        return new ResponseEntity<Utente>(utenteService.findByEmail(email), HttpStatus.OK);
    }


    @PostMapping(path = "/userJSONByEmail")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<Utente> findUserByEmail(@RequestBody Object parametri) throws JsonProcessingException {
        try {
            result = new ResponseEntity<Utente>(utenteService.findUserByEmail(parametri), HttpStatus.OK);
            logger.info("UTENTI - findByEmail - Status: 200");
            return new ResponseEntity<Utente>(utenteService.findUserByEmail(parametri), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Message: "+e.getMessage()+" - Value: "+e.getCause());
            logger.error("UTENTI - findByEmail - Status: "+HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    ResponseEntity<Utente> saveOne(@RequestBody Utente utente) {
        Utente utente1 = utenteService.saveOne(utente);
        return new ResponseEntity<>(utente1, HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity<Utente> changeOne(@RequestBody Utente newUtente, @PathVariable Integer id) {
        return new ResponseEntity<>(utenteService.changeOne(newUtente, id), HttpStatus.OK);
    }


    @PutMapping(path = "cambioPassword/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity<Utente> updateCambioPassword(@RequestBody Object parametri, @PathVariable Integer id) throws JsonProcessingException {
        return new ResponseEntity<>(utenteService.updateCambioPassword(parametri, id), HttpStatus.OK);
    }


    @DeleteMapping(path="/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    ResponseEntity<Object> deleteOne(@PathVariable Integer id) {
        String message = "Utente con id =>"+id+" è stato cancellato con successo!";
        Map<String, String> deleteMessage = new HashMap<>();
        deleteMessage.put("message", message);
        utenteService.deleteOne(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }
}
