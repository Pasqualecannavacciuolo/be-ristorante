package com.ristorante.beristorante.domain;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name="menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Integer id;
    private String nome;
    private Boolean attivo;
    //private Boolean disponibile;
    
    /*@OneToMany
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Set<Piatto> piatto;*/

    @ManyToMany
    @JsonIgnore
    // JsonBackReference & JsonManagedReference servono per ottenere nella risposta JSON l'associazione MANYTOMANY
    @JsonBackReference
    @JsonManagedReference 
    @JoinTable(name = "piatto_menu", 
            joinColumns = { @JoinColumn(name = "id_menu") }, 
            inverseJoinColumns = { @JoinColumn(name = "id_piatto") })
    private List<Piatto> lista_piatti;

    
}
