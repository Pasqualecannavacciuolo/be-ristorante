package com.ristorante.beristorante.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static com.ristorante.beristorante.enums.Permission.ADMIN_CREATE;
import static com.ristorante.beristorante.enums.Permission.ADMIN_DELETE;
import static com.ristorante.beristorante.enums.Permission.ADMIN_READ;
import static com.ristorante.beristorante.enums.Permission.ADMIN_UPDATE;
import static com.ristorante.beristorante.enums.Permission.MANAGER_CREATE;
import static com.ristorante.beristorante.enums.Permission.MANAGER_DELETE;
import static com.ristorante.beristorante.enums.Permission.MANAGER_READ;
import static com.ristorante.beristorante.enums.Permission.MANAGER_UPDATE;
import static com.ristorante.beristorante.enums.Role.ADMIN;
import static com.ristorante.beristorante.enums.Role.MANAGER;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .antMatchers("/ristorante/auth/**")
            .permitAll()
            .antMatchers("/ristorante/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
            .antMatchers(GET, "/ristorante/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
            .antMatchers(POST, "/ristorante/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
            .antMatchers(PUT, "/ristorante/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
            .antMatchers(DELETE, "/ristorante/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        //http.cors();
        return http.build();
    }
}
