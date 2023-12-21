package com.wiss.m223.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AdminController ist ein REST-Controller, der HTTP-Anfragen im Zusammenhang mit Admin-Funktionen behandelt.
 * Er verwendet die CrossOrigin-Anmerkung, um die Freigabe von Ressourcen über Ursprünge hinweg zu ermöglichen.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * Diese Methode behandelt GET-Anfragen, um eine Admin-Nachricht abzurufen.
     * Sie gibt eine Nachricht mit dem Inhalt "Admin" und einen OK-Status zurück.
     * @return ResponseEntity<String> - eine Nachricht mit dem Inhalt "Admin" und einen OK-Status.
     */
    @GetMapping("")
    public ResponseEntity<String> getAdmin() {
        return new ResponseEntity<>("Admin", HttpStatus.OK);
    }
}