package com.example.alstarboard.domain;

import lombok.*;
import java.sql.Timestamp;

@Builder
@ToString
@Setter
@Getter
public class Board {
    private Long id;
    private String title;
    private Image image; // Image 테이블의 외래 키
    private String text;
    private Long like;
    private Long unlike;
    private User user; // User 테이블의 외래 키
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
