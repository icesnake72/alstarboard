package com.example.alstarboard.repository;

import com.example.alstarboard.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findAll();
}
