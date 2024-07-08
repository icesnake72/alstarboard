package com.example.alstarboard.repository;

import com.example.alstarboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
  public List<Board> findAll();
  public List<Board> findByUserId(Long userId);
  public List<Board> findByUserUserEmail(String userEmail);
  public Board save(Board board);


}
