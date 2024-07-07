package com.example.alstarboard.domain;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    private Long id;
    private String userEmail;
    private String password;
    private String nickName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
