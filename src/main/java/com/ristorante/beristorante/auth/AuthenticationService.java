package com.ristorante.beristorante.auth;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ristorante.beristorante.config.JwtService;
import com.ristorante.beristorante.domain.Utente;
import com.ristorante.beristorante.repository.UtenteRepository;

@Service
public class AuthenticationService {
    @Autowired
    UtenteRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    long millis = System.currentTimeMillis();  
    Date data_odierna = new Date(millis);  

    public AuthenticationResponse register(RegisterRequest request) {
        Utente user = Utente.builder()
            .nome_utente(request.getNome()+"_"+request.getCognome())
            .salt("secret_salt")
            .password(passwordEncoder.encode(request.getPassword()))
            .modificato_da(request.getModificato_da())
            .modificato_il(data_odierna)
            .creato_il(data_odierna)
            .creato_da(request.getCreato_da())
            .ultima_modifica_password(data_odierna)
            .nome(request.getNome())
            .cognome(request.getCognome())
            .email(request.getEmail())
            .cambio_password(false)
            .ultimo_accesso(data_odierna)
            .role(request.getRole())
            .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
            .builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), 
                request.getPassword()
            )
        );
        Utente user = repository.findByEmail(request.getEmail())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
            .builder()
            .token(jwtToken)
            .build();
    }
    
}
