package com.example.alstarboard.dto;

import com.example.alstarboard.entity.Category;
import lombok.Data;

@Data
public class CategoryDTO {
  private Long id;
  private String name;
  private String desc;

  public static CategoryDTO of(Category category) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.id = category.getId();
    categoryDTO.name = category.getName();
    categoryDTO.desc = category.getDesc();
    return categoryDTO;
  }
}
