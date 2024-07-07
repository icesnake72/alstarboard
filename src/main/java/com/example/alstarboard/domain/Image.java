package com.example.alstarboard.domain;

import lombok.*;

import java.sql.Timestamp;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Image {
    private Long id;
    private String path;
    private String url;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
