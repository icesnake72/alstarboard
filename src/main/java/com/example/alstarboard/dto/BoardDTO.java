package com.example.alstarboard.dto;

import com.example.alstarboard.entity.Board;
import com.example.alstarboard.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardDTO {
  private Long id;
  private String title;
  private String text;
  private Long like;
  private Long unlike;
  private Long userId;
  private String userEmail;
  private String userNickName;
  private List<String> imageUrls;

  // getters and setters

  public static BoardDTO fromEntity(Board board) {
    BoardDTO dto = new BoardDTO();

    dto.setId(board.getId());
    dto.setTitle(board.getTitle());
    dto.setText(board.getText());
    dto.setLike(board.getLike());
    dto.setUnlike(board.getUnlike());
    dto.setUserId(board.getUser().getId());
    dto.setUserEmail(board.getUser().getUserEmail());
    dto.setUserNickName(board.getUser().getNickName());
    dto.setImageUrls(board.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));

    return dto;
  }
}
