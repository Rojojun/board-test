package com.example.collaborativewhiteboard.model;

import lombok.Data;

@Data
public class Coordinates {
    // 좌표와 같이 실질적으로 캔버스에 그려질 부분들
    private Long x0;
    private Long y0;
    private Long x1;
    private Long y1;

    private String color;
    private String username;
    private String lineWidth;
    private String instrument;
}
