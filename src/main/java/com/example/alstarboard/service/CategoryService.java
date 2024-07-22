package com.example.alstarboard.service;

import com.example.alstarboard.dto.CategoryDTO;
import com.example.alstarboard.entity.Category;
import com.example.alstarboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<CategoryDTO> getCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream()
        .map(CategoryDTO::of)
        .collect(Collectors.toList());
  }
}
