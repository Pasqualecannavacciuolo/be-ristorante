package com.ristorante.beristorante.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ristorante.beristorante.config.JwtService;
import com.ristorante.beristorante.domain.Utente_Auth;
import com.ristorante.beristorante.enums.Role;
import com.ristorante.beristorante.repository.UtenteAuthRepository;

@Service
public class AuthenticationService {
    @Autowired
    UtenteAuthRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Utente_Auth user = Utente_Auth.builder()
            .nome(request.getNome())
            .cognome(request.getCognome())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
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
        Utente_Auth user = repository.findByEmail(request.getEmail())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
            .builder()
            .token(jwtToken)
            .build();
    }
    
}
