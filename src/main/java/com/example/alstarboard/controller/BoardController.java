package com.example.alstarboard.controller;

import com.example.alstarboard.dto.BoardDTO;
import com.example.alstarboard.entity.Board;
import com.example.alstarboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    // 127.0.0.1:8080/api/board/page?page=0&pageSize=10
    @GetMapping("/page")
    public Page<BoardDTO> getBoardsByPage(
            @RequestParam int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return boardService.getBoardsByPage(page, pageSize);
    }

    @PostMapping("/create")
    public Board createBoardWithImages(
        @RequestParam("title") String title,
        @RequestParam("text") String text,
        @RequestParam("userId") Long userId,
        @RequestParam("files") List<MultipartFile> files) throws IOException {
        System.out.println("title = " + title);
        System.out.println("text = " + text);
        System.out.println("userId = " + userId);
        files.forEach(file -> System.out.println("file = " + file.getOriginalFilename()));
        return boardService.saveBoardWithImages(title, text, userId, files);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("Board deleted successfully");
    }

    @PostMapping("/{boardId}/like")
    public ResponseEntity<Board> updateLike(@PathVariable Long boardId, @RequestParam int value) {
        Board updatedBoard = boardService.updateLike(boardId, value);
        return ResponseEntity.ok(updatedBoard);
    }

    @PostMapping("/{boardId}/unlike")
    public ResponseEntity<Board> updateUnlike(@PathVariable Long boardId, @RequestParam int value) {
        Board updatedBoard = boardService.updateUnlike(boardId, value);
        return ResponseEntity.ok(updatedBoard);
    }
}
