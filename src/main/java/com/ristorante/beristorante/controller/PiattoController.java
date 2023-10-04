package com.ristorante.beristorante.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ristorante.beristorante.domain.Piatto;
import com.ristorante.beristorante.service.PiattoService;

@RestController
@RequestMapping("/ristorante/management/piatti")
@PreAuthorize("hasRole('ADMIN')")
public class PiattoController {
    
    @Autowired
    PiattoService piattoService;

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<?> findAll() {
        return new ResponseEntity<>(piattoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<Piatto> findById(@PathVariable Integer id) {
        Piatto piatto = piattoService.findById(id);
        return new ResponseEntity<Piatto>(piatto, HttpStatus.OK);
    }

    @GetMapping(path = "byMenuID/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<?> findByMenuId(@PathVariable Integer id) {
        List<Piatto> piatti = piattoService.findByMenuId(id);
        return new ResponseEntity<>(piatti, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    ResponseEntity<Piatto> saveOne(@RequestBody Piatto piatto) {
        Piatto piatto1 = piattoService.saveOne(piatto);
        return new ResponseEntity<>(piatto1, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity<Piatto> changeOne(@RequestBody Piatto newPiatto, @PathVariable Integer id) {
        return new ResponseEntity<>(piattoService.changeOne(newPiatto, id), HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    ResponseEntity<Object> deleteOne(@PathVariable Integer id) {
        String message = "Piatto con id =>"+id+" è stato cancellato con successo!";
        Map<String, String> deleteMessage = new HashMap<>();
        deleteMessage.put("message", message);
        piattoService.deleteOne(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }
}
