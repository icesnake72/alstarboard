package com.example.alstarboard.repository;

import com.example.alstarboard.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> selectAll();
    User selectByEmail(@Param("userEmail") String userEmail);
    void insertUser(User user);
}
