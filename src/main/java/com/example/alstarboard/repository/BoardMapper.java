package com.example.alstarboard.repository;

import com.example.alstarboard.domain.Board;
import com.example.alstarboard.domain.Image;
import com.example.alstarboard.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
@RequiredArgsConstructor
public class BoardMapper {
    private final SqlSessionTemplate sql;
    public void insertBoard(Board board) {
        sql.insert("BoardMapper.insertBoard", board);
    }

//    int countAll();
//
//    List<Board> selectBoardByUserId(@Param("userId") Long userId);
//
////    List<Board> selectBoardsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
//    Image selectImageById(@Param("id")Long id);
//    User selectUserById(@Param("id")Long id);
}
