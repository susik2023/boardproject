package com.sparta.boardproject.board.repository;

import com.sparta.boardproject.board.entity.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

  List<Board> findAllByOrderByCreatedDateDesc();

  @Query("select b from Board b join fetch b.user where b.boardId = :boardId")
  Optional<Board> findByBoardIdWithUser(@Param("boardId") Long boardId);
}
