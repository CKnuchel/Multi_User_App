package com.wiss.m223.Util;

import com.wiss.m223.Config.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

// Diese Klasse hat 3 Hauptfunktionen:
// generateJwtToken: Erstellt ein JWT-Token aus einem Auth-Objekt
// getUserNameFromJwtToken: Holt den Benutzernamen aus dem JWT
// validateJwtToken: Validiert ein JWT mit einem Geheimnis
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // Liest den JWT-Schlüssel aus der Anwendungskonfiguration
    @Value("${m223.app.jwtSecret}")
    private String jwtSecret;

    // Liest die JWT-Ablaufzeit aus der Anwendungskonfiguration
    @Value("${m223.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Generiert ein JWT-Token basierend auf den Authentifizierungsdetails
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()
                        + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Extrahiert den Benutzernamen aus dem JWT-Token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Überprüft die Gültigkeit des JWT-Tokens
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Ungültige JWT-Signatur: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Ungültiges JWT-Token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT-Token ist abgelaufen: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT-Token wird nicht unterstützt: {}",
                    e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT-Ansprüche sind leer: {}",
                    e.getMessage());
        }
        return false;
    }
}