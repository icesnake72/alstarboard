package com.example.alstarboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board_category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(length = 100)
  private String desc;

  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp updatedAt;
}
