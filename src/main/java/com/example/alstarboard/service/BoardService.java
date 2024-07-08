package com.example.alstarboard.service;

import com.example.alstarboard.dto.BoardDTO;
import com.example.alstarboard.entity.Board;
import com.example.alstarboard.entity.Image;
import com.example.alstarboard.entity.User;
import com.example.alstarboard.repository.BoardRepository;
import com.example.alstarboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Value("${image.access.url}")
    private String accessUrl;

    @Transactional(readOnly = true)
    public Page<BoardDTO> getBoardsByPage(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by( "createdAt").descending());
        return boardRepository.findAll(pageRequest).map(BoardDTO::fromEntity);
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Board saveBoardWithImages(String title, String text, Long userId, List<MultipartFile> files) throws IOException {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Board board = Board.builder()
            .title(title)
            .text(text)
            .user(user)
            .build();

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;
            String fileUrl = accessUrl + "/" + fileName;

            File dest = new File(filePath);
            file.transferTo(dest);

            images.add(Image.builder()
                .url(fileUrl)
                .path(filePath)
                .board(board)
                .build());
        }
        board.setImages(images);

        return boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("Board not found"));

        // 실제 파일 시스템에서 이미지 파일 삭제
        for (Image image : board.getImages()) {
            File file = new File(image.getPath());
            if (file.exists()) {
                file.delete();
            }
        }

        // 데이터베이스에서 게시글과 연관된 이미지 삭제
        boardRepository.delete(board);
    }

    public Board updateLike(Long boardId, int value) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setLike(board.getLike() + value);
        return boardRepository.save(board); // save() 메서드가 update 로 작동함
    }

    public Board updateUnlike(Long boardId, int value) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setUnlike(board.getUnlike() + value);
        return boardRepository.save(board); // save() 메서드가 update 로 작동함
    }
}
