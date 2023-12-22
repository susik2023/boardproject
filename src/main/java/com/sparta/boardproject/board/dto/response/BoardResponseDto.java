package com.sparta.boardproject.board.dto.response;


import com.sparta.boardproject.board.entity.Board;
import java.time.LocalDateTime;

public record BoardResponseDto(
    Long boardId,
    String boardTitle,
    String boardContent,
    String userName,
    LocalDateTime createdDate,
    LocalDateTime modifiedDate
) {

  public static BoardResponseDto from(Board board) {
    return new BoardResponseDto(
        board.getBoardId(),
        board.getBoardTitle(),
        board.getBoardContent(),
        board.getUser().getUsername(),
        board.getCreatedDate(),
        board.getModifiedDate()
    );
  }
}
