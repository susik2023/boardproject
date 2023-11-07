package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {

        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto){

        return boardService.createBoard(boardRequestDto);
    }
    @GetMapping("/boards")
    public List<BoardResponseDto> getallBoard(){

        return boardService.getAllBoard();
    }
    @GetMapping("/boards/{id}")
    public Board getBoard(@PathVariable Long id){

        return boardService.getBoard(id);
    }


    @PutMapping("/boards/{id}")
    public Long updateBoard(@PathVariable Long id,@RequestBody BoardRequestDto boardRequestDto){

        return boardService.updateBoard(id, boardRequestDto);
    }

    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@PathVariable Long id,@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.deleteBoard(id, boardRequestDto);
    }




}
