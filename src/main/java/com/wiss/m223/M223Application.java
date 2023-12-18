package com.wiss.m223;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Hauptklasse der Spring Boot-Anwendung
@SpringBootApplication
public class M223Application {

 // Hauptmethode, die beim Start der Anwendung ausgeführt wird
 public static void main(String[] args) {
  // Startet die Spring Boot-Anwendung
  SpringApplication.run(M223Application.class, args);
 }

 /**
  * Aktiviert CORS-Anfragen vom React-Frontend
  * @return Ein WebMvcConfigurer-Objekt, das die CORS-Konfiguration enthält
  */
 @Bean
 public WebMvcConfigurer corsConfigurer() {
  return new WebMvcConfigurer() {
   // Überschreibt die Methode addCorsMappings, um die CORS-Konfiguration festzulegen
   @Override
   public void addCorsMappings(CorsRegistry registry) {
    // Erlaubt CORS-Anfragen für verschiedene Endpunkte von der angegebenen Origin
    registry.addMapping("/public").allowedOrigins("http://localhost:5173");
    registry.addMapping("/private").allowedOrigins("http://localhost:5173");
    registry.addMapping("/api/auth/signin").allowedOrigins("http://localhost:5173");
    registry.addMapping("/questions/**").allowedOrigins("http://localhost:5173");
    registry.addMapping("/answers/**").allowedOrigins("http://localhost:5173");
    registry.addMapping("/responses/**").allowedOrigins("http://localhost:5173");
    registry.addMapping("/responses").allowedOrigins("http://localhost:5173");
   }
  };
 }

}