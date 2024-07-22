package com.example.alstarboard.dto;

import com.example.alstarboard.entity.User;
import lombok.Data;

@Data
public class UserDTO {
  private Long userId;
  private String userEmail;
  private String userNickname;

  static public UserDTO of(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(user.getId());
    userDTO.setUserEmail(user.getUserEmail());
    userDTO.setUserNickname(user.getNickName());
    return userDTO;
  }
}
