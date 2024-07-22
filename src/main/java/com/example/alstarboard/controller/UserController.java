package com.example.alstarboard.controller;

import com.example.alstarboard.dto.UserDTO;
import com.example.alstarboard.entity.User;
import com.example.alstarboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public List<UserDTO> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/{id}")
  public UserDTO getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/create")
  public UserDTO createUser(@RequestParam("email") String userEmail,
                            @RequestParam("nick_name") String userNickname) {
    return userService.createUser(userEmail, userNickname);
  }

  @PostMapping("/update")
  public UserDTO updateUser(@RequestParam("id") Long userId,
                            @RequestParam("email") String userEmail,
                            @RequestParam("nick_name") String userNickname) {
    return userService.updateUser(userId, userEmail, userNickname);
  }
}
