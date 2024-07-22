package com.example.alstarboard.service;

import com.example.alstarboard.dto.BoardDTO;
import com.example.alstarboard.dto.request.BoardRequest;
import com.example.alstarboard.entity.Board;
import com.example.alstarboard.entity.Image;
import com.example.alstarboard.entity.User;
import com.example.alstarboard.repository.BoardRepository;
import com.example.alstarboard.repository.UserRepository;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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
    private final ServletContext servletContext;

    // application.yaml 파일에서 설정한 값을 가져온다
    @Value("${image.upload.dir}")
    private String uploadDir;

    @Value("${image.access.url}")
    private String accessUrl;

    @Transactional(readOnly = true)
    public Page<BoardDTO> getBoardsByPage(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by( "createdAt").descending());

        // Entity -> DTO 변환, 왜? Entity가 기능이 많아서 무거우니까...
        return boardRepository.findAll(pageRequest).map(BoardDTO::fromEntity);
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional
    public Board saveBoardWithImages(BoardRequest board) throws IOException {
        // userId를 이용하여 user 테이블의 해당 사용자 정보를 가져온다
        User user = userRepository.findById(board.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // BoardRequest instance를 이용하여 Board instance를 생성한다
        Board newBoard = Board.builder()
            .title(board.getTitle())
            .text(board.getText())
            .user(user)
            .build();

        // 실제 경로 가져오기
        File staticImagesDir = new ClassPathResource("static/images").getFile();
        String realPath = staticImagesDir.getAbsolutePath();

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : board.getFiles()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = realPath + File.separator + fileName;
            String fileUrl = accessUrl + "/" + fileName;

            // logging
            System.out.println(filePath);

            File dest = new File(filePath);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);

            images.add(Image.builder()
                .url(fileUrl)
                .path(filePath)
                .board(newBoard)
                .build());
        }
        // Board와 Image의 관계 설정, board에 이미지 리스트를 저장
        newBoard.setImages(images);

        return boardRepository.save(newBoard);
    }

    @Transactional
    public Board saveBoardWithImages(String title, String text, Long userId, List<MultipartFile> files) throws IOException {
        // userId를 이용하여 user 테이블의 해당 사용자 정보를 가져온다
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));


        Board board = Board.builder()
            .title(title)
            .text(text)
            .user(user)
            .build();

        // 실제 경로 가져오기
        File staticImagesDir = new ClassPathResource("static/images").getFile();
        String realPath = staticImagesDir.getAbsolutePath();

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = realPath + File.separator + fileName;
            String fileUrl = accessUrl + "/" + fileName;

            System.out.println(filePath);

            File dest = new File(filePath);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);

            images.add(Image.builder()
                .url(fileUrl)
                .path(filePath)
                .board(board)
                .build());
        }
        // Board와 Image의 관계 설정, board에 이미지 리스트를 저장
        board.setImages(images);

        return boardRepository.save(board);
    }

    @Transactional
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

    @Transactional
    public Board updateLike(Long boardId, int value) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setLike(board.getLike() + value);
        return boardRepository.save(board); // save() 메서드가 update 로 작동함
    }

    @Transactional
    public Board updateUnlike(Long boardId, int value) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setUnlike(board.getUnlike() + value);
        return boardRepository.save(board); // save() 메서드가 update 로 작동함
    }
}
