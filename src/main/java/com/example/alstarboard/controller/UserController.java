package com.example.alstarboard.controller;

import com.example.alstarboard.dto.UserDTO;
import com.example.alstarboard.dto.request.UserRequest;
import com.example.alstarboard.entity.User;
import com.example.alstarboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

  @PostMapping("/login")
  public ResponseEntity<UserDTO> login(@ModelAttribute UserRequest userRequest) {
    if (userRequest.getUserEmail().isEmpty() || userRequest.getPassword().isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    System.out.println(userRequest.getUserEmail());
    System.out.println(userRequest.getPassword());

    Optional<UserDTO> userOpt = userService.login(userRequest);
    return userOpt.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.internalServerError().build());
  }
}
