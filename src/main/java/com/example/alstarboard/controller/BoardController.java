package com.example.alstarboard.controller;

import com.example.alstarboard.dto.BoardDTO;
import com.example.alstarboard.dto.request.BoardRequest;
import com.example.alstarboard.dto.CategoryDTO;
import com.example.alstarboard.entity.Board;
import com.example.alstarboard.service.BoardService;
import com.example.alstarboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;

    @GetMapping
    public String getBoards() {
        return "board";
    }


    @GetMapping("/page")
    public Page<BoardDTO> getBoardsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return boardService.getBoardsByPage(page, pageSize);
    }

//    @PostMapping("/create")
//    public Board createBoardWithImages(
//        @RequestParam("title") String title,
//        @RequestParam("text") String text,
//        @RequestParam("userId") Long userId,
//        @RequestParam("files") List<MultipartFile> files) throws IOException {
//        System.out.println("title = " + title);
//        System.out.println("text = " + text);
//        System.out.println("userId = " + userId);
//        files.forEach(file -> System.out.println("file = " + file.getOriginalFilename()));
//        return boardService.saveBoardWithImages(title, text, userId, files);
//    }
    @PostMapping("/create")
    public ResponseEntity<?> createBoardWithImages(@ModelAttribute BoardRequest boardRequest) {
        if (boardRequest.getFiles().isEmpty()) {
            return ResponseEntity.badRequest().body("No files uploaded");
        }

        if (boardRequest.getTitle().isEmpty() ||
            boardRequest.getText().isEmpty() ||
            boardRequest.getUserId() == null) {
            return ResponseEntity.badRequest().body("Title or text is empty");
        }

        // logging
        System.out.println("title = " + boardRequest.getTitle());
        System.out.println("text = " + boardRequest.getText());
        System.out.println("userId = " + boardRequest.getUserId());
        boardRequest.getFiles().forEach(file -> System.out.println("file = " + file.getOriginalFilename()));
        // end of logging

        try {
            Board board = boardService.saveBoardWithImages(boardRequest);
            return ResponseEntity.ok(board);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to save board with images");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BoardDTO>> search(@RequestParam(defaultValue = "") String keyword,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "20") int pageSize) {
        if (keyword.isEmpty()) {
            ResponseEntity.badRequest().build();
        }

        Page<BoardDTO> pageBoard = boardService.search(keyword, keyword, page, pageSize);
        if ( pageBoard!=null ) {
            return ResponseEntity.ok(pageBoard);
        }

        return ResponseEntity.internalServerError().build();
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

    // /api/board/categories
    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }
}
