package com.sparta.board.dto;

import com.sparta.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String boardname;
    private String writer;
    private String content;


    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardname = board.getBoardname();
        this.writer = board.getWriter();
        this.content = board.getContent();

    }
}
