package com.ristorante.beristorante.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ristorante.beristorante.domain.Menu;
import com.ristorante.beristorante.domain.Piatto;
import com.ristorante.beristorante.service.MenuService;
import com.ristorante.beristorante.service.PiattoService;

@RestController
@RequestMapping("/ristorante/management/menu")
@PreAuthorize("hasRole('ADMIN')")
public class MenuController {
    
    @Autowired
    MenuService menuService;
    @Autowired
    PiattoService piattoService;

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<?> findAll() {
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    ResponseEntity<Menu> findById(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        return new ResponseEntity<Menu>(menu, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    ResponseEntity<Menu> saveOne(@RequestBody Menu menu) {
        Menu menu1 = menuService.saveOne(menu);
        return new ResponseEntity<>(menu1, HttpStatus.OK);
    }

    @PostMapping("/addPiatti/{id}")
    @PreAuthorize("hasAuthority('admin:create')")
    ResponseEntity<Menu> addPiatti(@RequestBody Object lista_piatti, @PathVariable Integer id) throws JsonProcessingException{
        
        Menu menu_where_to_add = menuService.findById(id);
        Menu new_menu_where_to_add = menuService.addPiatti(menu_where_to_add, lista_piatti);
        return new ResponseEntity<>(new_menu_where_to_add,HttpStatus.OK);
    }

    /*@PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity<Menu> changeOne(@RequestBody Object parametri, @PathVariable Integer id) throws JsonProcessingException {
        return new ResponseEntity<>(menuService.changeOne(parametri, id), HttpStatus.OK);
    }*/

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity<Menu> changeMenu(@RequestBody Object parametri, @PathVariable Integer id) throws JsonProcessingException {
        return new ResponseEntity<>(menuService.changeMenu(parametri, id), HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    ResponseEntity<Object> deleteOne(@PathVariable Integer id) {
        String message = "Menu con id =>"+id+" è stato cancellato con successo!";
        Map<String, String> deleteMessage = new HashMap<>();
        deleteMessage.put("message", message);
        menuService.deleteOne(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }
}