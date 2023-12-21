package com.wiss.m223.Security;

import com.wiss.m223.Filter.AuthTokenFilter;
import com.wiss.m223.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Definiert die Klasse als Konfiguration, was bedeutet, dass sie Spring-Beans bereitstellt
@Configuration
// Aktiviert die Web-Sicherheit
@EnableWebSecurity
public class WebSecurityConfig {
    // Injiziert die UserDetailsServiceImpl und AuthenticationEntryPoint
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler;

    // Definiert die Zugriffsrechte für verschiedene Endpunkte
    private final static String[] EVERYONE = {
            "/public",
            "api/auth/**", "/api/auth/signup", "/api/auth/signin"
    };
    private final static String[] SECURE = { "/questions", "/questions/**",
            "/answers", "/answers/**",
            "/responses", "/responses/**" };
    private final static String[] ROLES = { "MODERATOR", "ADMIN" };

    // Erstellt einen Bean für den AuthTokenFilter
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // Erstellt einen Bean für den DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Erstellt einen Bean für den AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Erstellt einen Bean für den PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Erstellt einen Bean für die SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults()) // CORS aktivieren
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)) // Fehlerbehandlung
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Session-Management
                .authorizeHttpRequests(auth -> auth.requestMatchers(EVERYONE).permitAll() // Zugriff für alle
                        .anyRequest().authenticated()); // Authentifizierung für alle anderen
        http.authenticationProvider(authenticationProvider()); // Authentifizierung
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
        return http.build(); // FilterChain erstellen
    }
}