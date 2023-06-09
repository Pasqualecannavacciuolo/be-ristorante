package com.ristorante.beristorante.auth;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    private String nome_utente;
    private String salt;
    private String password;
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
}
