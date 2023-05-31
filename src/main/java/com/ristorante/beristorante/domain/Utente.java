package com.ristorante.beristorante.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.ristorante.beristorante.enums.Role;

@Entity
@Table(name="utente")
public class Utente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome_utente;
    private String salt;
    private String hash;
    private Integer  modificato_da;
    private Date modificato_il;
    private Date creato_il;
    private Integer creato_da;
    private Date ultima_modifica_password;
    private String nome;
    private String cognome;
    private String email;
    private Boolean cambio_password;
    private Date ultimo_accesso;
    @Enumerated(EnumType.STRING)
    private Role role;

    // Costruttore di default
    public Utente(){}

    // Costruttore con tutti i campi
    public Utente(Integer id, String nome_utente, String salt, String hash, Integer modificato_da, Date modificato_il, Date creato_il, Integer creato_da, Date ultima_modifica_password, String nome, String cognome, String email, Boolean cambio_password, Date ultimo_accesso) {
        this.id = id;
        this.nome_utente = nome_utente;
        this.salt = salt;
        this.hash = hash;
        this.modificato_da = modificato_da;
        this.modificato_il = modificato_il;
        this.creato_il = creato_il;
        this.creato_da = creato_da;
        this.ultima_modifica_password = ultima_modifica_password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.cambio_password = cambio_password;
        this.ultimo_accesso = ultimo_accesso;
        this.role = Role.USER;
    }

    /* GETTER & SETTER */

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_utente() {
        return this.nome_utente;
    }

    public void setNome_utente(String nome_utente) {
        this.nome_utente = nome_utente;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getModificato_da() {
        return this.modificato_da;
    }

    public void setModificato_da(Integer modificato_da) {
        this.modificato_da = modificato_da;
    }

    public Date getModificato_il() {
        return this.modificato_il;
    }

    public void setModificato_il(Date modificato_il) {
        this.modificato_il = modificato_il;
    }

    public Date getCreato_il() {
        return this.creato_il;
    }

    public void setCreato_il(Date creato_il) {
        this.creato_il = creato_il;
    }

    public Integer getCreato_da() {
        return this.creato_da;
    }

    public void setCreato_da(Integer creato_da) {
        this.creato_da = creato_da;
    }

    public Date getUltima_modifica_password() {
        return this.ultima_modifica_password;
    }

    public void setUltima_modifica_password(Date ultima_modifica_password) {
        this.ultima_modifica_password = ultima_modifica_password;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isCambio_password() {
        return this.cambio_password;
    }

    public Boolean getCambio_password() {
        return this.cambio_password;
    }

    public void setCambio_password(Boolean cambio_password) {
        this.cambio_password = cambio_password;
    }

    public Date getUltimo_accesso() {
        return this.ultimo_accesso;
    }

    public void setUltimo_accesso(Date ultimo_accesso) {
        this.ultimo_accesso = ultimo_accesso;
    }

    



}
