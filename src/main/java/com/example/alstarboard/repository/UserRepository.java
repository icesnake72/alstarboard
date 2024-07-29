package com.example.alstarboard.repository;

import com.example.alstarboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserEmail(String email);
  Optional<User> findByUserEmailAndPassword(String email, String password);
}
