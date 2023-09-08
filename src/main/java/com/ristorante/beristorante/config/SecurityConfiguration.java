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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

import java.util.Arrays;

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
            .cors()
            .and()
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://192.168.1.119", "http://192.168.1.119:4200"));
        // or any domain that you want to restrict to
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        // Add the method support as you like
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
