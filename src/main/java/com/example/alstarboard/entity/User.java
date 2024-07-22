package com.example.alstarboard.entity;

import com.example.alstarboard.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.bytecode.enhance.spi.EnhancementInfo;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String userEmail;

    @Column(length = 20)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickName;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp updatedAt;

    public static User fromUserDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getUserId())
                .userEmail(userDTO.getUserEmail())
                .nickName(userDTO.getUserNickname())
                .build();
    }
}