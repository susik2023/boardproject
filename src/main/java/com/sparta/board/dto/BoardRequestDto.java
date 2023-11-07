package com.sparta.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {

    private String boardname;
    private String writer;
    private String content;
    private String password;
}
