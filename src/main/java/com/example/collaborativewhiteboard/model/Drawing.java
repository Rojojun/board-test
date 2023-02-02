package com.example.collaborativewhiteboard.model;

import lombok.Data;

import java.util.List;

@Data
public class Drawing {
    // Coordinates : Dots + Coordinates + ... = line
    private List<Coordinates> line;
}
