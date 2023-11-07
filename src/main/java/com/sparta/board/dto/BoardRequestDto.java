package com.sparta.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class BoardRequestDto {

    private String boardname;
    private String writer;
    private String content;
    private String password;
}
