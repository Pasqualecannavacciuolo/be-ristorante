package com.ristorante.beristorante.controller;

import com.ristorante.beristorante.domain.Utente;
import com.ristorante.beristorante.service.UtenteService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ristorante/management/utenti")
@PreAuthorize("hasRole('ADMIN')")
public class UtenteController {
    @Autowired
    UtenteService utenteService;
    
    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<?> findAll() {
        return new ResponseEntity<>(utenteService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<Utente> findById(@PathVariable Integer id) {
        Utente utente = utenteService.findById(id);
        return new ResponseEntity<Utente>(utente, HttpStatus.OK);
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
