package com.example.alstarboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
  private String title;
  private String text;
  private Long userId;
  private List<String> imageUrls;
}
