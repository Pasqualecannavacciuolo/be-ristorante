package com.ristorante.beristorante.controller;

import com.ristorante.beristorante.domain.Utente;
import com.ristorante.beristorante.service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ristorante")
//@CrossOrigin(origins = "http://localhost:3000")
public class UtenteController {
    @Autowired
    UtenteService utenteService;

    @GetMapping(path = "/{id}")
    ResponseEntity<Utente> findById(@PathVariable Integer id) {
        Optional<Utente> utente = utenteService.findById(id);
        return new ResponseEntity<Utente>((Utente) utenteService.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    ResponseEntity<?> findAll() {
        return new ResponseEntity<>(utenteService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Utente> saveOne(@RequestBody Utente Utente) {
        Utente Utente1 = utenteService.saveOne(Utente);
        return new ResponseEntity<>(Utente1, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    ResponseEntity<Void> deleteOne(@PathVariable Integer id) {
        utenteService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
