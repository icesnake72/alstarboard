package com.example.alstarboard.service;

import com.example.alstarboard.domain.Board;
import com.example.alstarboard.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

//    public List<Board> getBoardsByPage(int page, int pageSize) {
//        int offset = (page - 1) * pageSize;
//        return boardMapper.selectBoardsByPage(offset, pageSize);
//    }

//    public int getTotalPages(int pageSize) {
//        int totalRecords = boardMapper.countAll();
//        return (int) Math.ceil((double) totalRecords / pageSize);
//    }
}
