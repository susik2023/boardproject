package com.sparta.boardproject.board.dto.request;


import com.sparta.boardproject.board.entity.Board;
import com.sparta.boardproject.user.entity.User;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoardRequestDto(
    @NotBlank
    @Size(max = 200)
    String boardTitle,

    @NotBlank
    @Size(max = 2000)
    @Lob
    String boardContent
) {

  public Board toEntity(User user) {
    return Board.builder()
        .boardTitle(boardTitle)
        .boardContent(boardContent)
        .user(user)
        .build();
  }
}
