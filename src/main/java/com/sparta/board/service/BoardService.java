package com.sparta.board.service;

import com.mysql.cj.exceptions.PasswordExpiredException;
import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto){

        Board board = new Board(boardRequestDto);

        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }
    public List<BoardResponseDto> getAllBoard() {

        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }


    @Transactional
    public Long updateBoard(Long id, BoardRequestDto boardRequestDto) {

        Board board = findBoard(id);

        board.update(boardRequestDto);
        return id;
    }

    public Long deleteBoard(Long id,BoardRequestDto boardRequestDto) {

        Board board = findBoard(id);
        if(board.getPassword()!=boardRequestDto.getPassword()){
            boardRepository.delete(board);;
            System.out.println("삭제 완료");
        }else throw new PasswordExpiredException("비밀번호가 일치하지 않습니다.");
        return id;

    }

    public Board findBoard(Long id) {

        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 보드가 존재하지 않습니다.")
        );
    }
}
