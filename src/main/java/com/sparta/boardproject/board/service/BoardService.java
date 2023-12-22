package com.sparta.boardproject.board.service;


import com.sparta.boardproject.board.dto.request.BoardRequestDto;
import com.sparta.boardproject.board.dto.request.BoardUpdateRequestDto;
import com.sparta.boardproject.board.dto.response.BoardResponseDto;
import com.sparta.boardproject.board.entity.Board;
import com.sparta.boardproject.board.repository.BoardRepository;
import com.sparta.boardproject.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  @Transactional
  public BoardResponseDto saveBoard(BoardRequestDto requestDto, User loginuser) {
    Board savedBoard = requestDto.toEntity(loginuser);
    boardRepository.save(savedBoard);
    return BoardResponseDto.from(savedBoard);
  }
  public BoardResponseDto getBoard(Long boardId) {
    Board getBoard = findByBoardId(boardId);
    return BoardResponseDto.from(getBoard);
  }

  public List<BoardResponseDto> getBoards() {
    List<Board> boards = boardRepository.findAllByOrderByCreatedDateDesc();
    return boards.stream().map(BoardResponseDto::from).collect(Collectors.toList());
  }

  @Transactional
  public BoardResponseDto updateBoard(Long boardId, BoardUpdateRequestDto requestDto) {
    Board updateBoard = findByBoardId(boardId);
    updateBoard.update(requestDto.toEntity());

    return BoardResponseDto.from(updateBoard);
  }
  public void deleteBoard(Long boardId) {
    findByBoardId(boardId);
    boardRepository.deleteById(boardId);
  }

  public Board findByBoardId(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException("보드를 찾을 수 없습니다."));
  }
  @Transactional
  public void updateBoard(Board updateBoard, Board board) {
    updateBoard.update(board);
  }


  public Long getAuthorIdByBoardId(Long boardId) {
    Board board = boardRepository.findByBoardIdWithUser(boardId).orElseThrow(IllegalArgumentException::new);
    return board.getUser().getUserId();
  }
}