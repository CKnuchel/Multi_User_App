package com.wiss.m223.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Diese Klasse dient als Einstiegspunkt für die Authentifizierung und behandelt Authentifizierungsfehler
// Sie implementiert das Interface AuthenticationEntryPoint und überschreibt die Methode commence()
// Diese Methode wird immer dann aufgerufen, wenn ein nicht authentifizierter Benutzer auf eine gesicherte HTTP-Ressource zugreift
// und eine AuthenticationException ausgelöst wird
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    // Logger zur Protokollierung von Informationen und Fehlern
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    // Überschreibt die Methode commence(), um auf Authentifizierungsfehler zu reagieren
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        // Protokolliert den Fehler
        logger.error("Unauthorized error: {}", authException.getMessage());
        try {
            // Sendet einen HTTP 401 Unauthorized Fehler an den Client
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        } catch (java.io.IOException e) {
            // Protokolliert den Fehler, wenn das Senden der Fehlerantwort fehlschlägt
            e.printStackTrace();
        }
    }
}