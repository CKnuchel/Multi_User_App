package com.wiss.m223.DTO;

// Diese Klasse repräsentiert eine Nachrichtenantwort, die in verschiedenen Teilen der Anwendung verwendet wird
public class MessageResponse {

    private String message; // Die Nachricht, die in der Antwort enthalten ist

    // Konstruktor für die MessageResponse-Klasse
    public MessageResponse(String messsage) {
        this.message = messsage;
    }

    // Getter-Methode für die Nachricht
    public String getMessage() {
        return message;
    }

    // Setter-Methode für die Nachricht
    public void setMessage(String message) {
        this.message = message;
    }

}