package com.example.collaborativewhiteboard.model;

import java.util.List;

public class ActionResponseDto {
    private String message;


    private List<String> users;

    public ActionResponseDto(String message, List<String> users) {
        this.message = message;
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
