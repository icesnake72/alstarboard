package com.example.alstarboard.dto.request;

import lombok.Data;

@Data
public class UserRequest {
  private String userEmail;
  private String password;
  private String userNickName;
}
