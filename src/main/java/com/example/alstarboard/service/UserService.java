package com.example.alstarboard.service;

import com.example.alstarboard.dto.UserDTO;
import com.example.alstarboard.entity.User;
import com.example.alstarboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDTO getUserById(Long id) {
    System.out.println("id: " + id);
    Optional<User> user = userRepository.findById(id);
    System.out.println("user: " + user.get());
    return user.isPresent() ? UserDTO.of(user.get()) : null;
  }

  public UserDTO getUserByEmail(String email) {
    Optional<User> user = userRepository.findByUserEmail(email);
    return user.map(UserDTO::of).orElse(null);
  }

  public UserDTO createUser(String userEmail, String userNickname) {
    User user = User.builder()
        .userEmail(userEmail)
        .nickName(userNickname)
        .build();

    User savedUser = userRepository.save(user);
    return UserDTO.of(savedUser);
  }

  public UserDTO updateUser(Long userId, String userEmail, String userNickname) {
    Optional<User> user = userRepository.findById(userId);
    if (user.isPresent()) {
      User updatedUser = user.get();
      updatedUser.setUserEmail(userEmail);
      updatedUser.setNickName(userNickname);
      User savedUser = userRepository.save(updatedUser);
      return UserDTO.of(savedUser);
    } else {
      return null;
    }
  }

  public List<UserDTO> getUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(UserDTO::of)
        .collect(Collectors.toList());
  }
}
