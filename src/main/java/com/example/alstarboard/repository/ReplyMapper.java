package com.example.alstarboard.repository;

import com.example.alstarboard.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplyMapper {
    void insertReply(Reply reply);
}
