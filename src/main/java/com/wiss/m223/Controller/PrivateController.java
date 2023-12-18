package com.wiss.m223.Controller;

// Importiert die notwendigen Spring Framework Bibliotheken
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Erlaubt Cross-Origin-Anfragen von allen Ursprüngen und setzt das maximale Alter auf 3600 Sekunden
@CrossOrigin(origins = "*", maxAge = 3600)
// Definiert die Klasse als RestController, was bedeutet, dass sie HTTP-Anfragen behandeln kann
@RestController
// Legt den Pfad fest, unter dem dieser Controller erreichbar ist
@RequestMapping("/private")
public class PrivateController {

    // Endpunkte hier einfügen

}