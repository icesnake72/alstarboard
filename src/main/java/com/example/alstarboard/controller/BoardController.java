package com.example.alstarboard.controller;

import com.example.alstarboard.domain.Board;
import com.example.alstarboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public String getBoards() {
        return "board";
    }

//    @GetMapping("/page")
//    public List<Board> getBoardsByPage(
//            @RequestParam int page,
//            @RequestParam(defaultValue = "20") int pageSize) {
//        return boardService.getBoardsByPage(page, pageSize);
//    }
}
