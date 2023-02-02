package com.example.collaborativewhiteboard.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Data
@Document
public class Room {
    // 그림을 실제로 보여줄 캔버스 및 여러가지를 담고 있는 화면
    // 클라이언트의 메인 화면
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private String address;

    List<String> participants;

    public List<Drawing> getDrawings() {
        return drawings;
    }

    List<Drawing> drawings;
}
