package com.wiss.m223.DTO;

public class MessageResponse {

    private String message;

    public MessageResponse(String messsage) {
        this.message = messsage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
