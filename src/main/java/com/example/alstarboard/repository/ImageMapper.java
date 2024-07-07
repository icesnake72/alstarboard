package com.example.alstarboard.repository;

import com.example.alstarboard.domain.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {

    void insertImage(Image image);
}
