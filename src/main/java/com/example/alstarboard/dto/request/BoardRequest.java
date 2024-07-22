package com.example.alstarboard.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardRequest {
  private String title;
  private String text;
  private Long userId;
  private List<MultipartFile> files;
}
