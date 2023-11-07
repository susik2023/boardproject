package com.sparta.board.entity;

import com.sparta.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String boardname;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String password;

    public Board(BoardRequestDto boardRequestDto) {
        this.boardname = boardRequestDto.getBoardname();
        this.writer = boardRequestDto.getWriter();
        this.content = boardRequestDto.getContent();
        this.password = boardRequestDto.getPassword();
    }
    public void update(BoardRequestDto boardRequestDto){
        this.boardname = boardRequestDto.getBoardname();
        this.writer = boardRequestDto.getWriter();
        this.content = boardRequestDto.getContent();
        this.password = boardRequestDto.getPassword();
    }


}
