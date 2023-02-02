package com.example.collaborativewhiteboard.model;

public class TextMessageDto {

    private String message;

    public TextMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
