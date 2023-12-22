package com.sparta.boardproject.board.controller;


import com.sparta.boardproject.board.dto.request.BoardRequestDto;
import com.sparta.boardproject.board.dto.request.BoardUpdateRequestDto;
import com.sparta.boardproject.board.dto.response.BoardResponseDto;
import com.sparta.boardproject.board.service.BoardService;
import com.sparta.boardproject.global.jwt.UserDetailsImpl;
import com.sparta.boardproject.user.entity.User;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

  private final BoardService boardService;

  @PostMapping
  public ResponseEntity<BoardResponseDto> saveBoard(
      @Valid @RequestBody BoardRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();

    BoardResponseDto responseDto = boardService.saveBoard(requestDto, loginUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // 게시글 단건 조회
  @GetMapping("/{boardId}")
  public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
    BoardResponseDto responseDto = boardService.getBoard(boardId);
    return ResponseEntity.ok(responseDto);
  }

  // 게시글 전체 목록 조회
  @GetMapping
  public ResponseEntity<List<BoardResponseDto>> getBoards() {
    List<BoardResponseDto> responseDto = boardService.getBoards();
    return ResponseEntity.ok(responseDto);
  }

  // 게시글 수정
  @PatchMapping("/{boardId}")
  public ResponseEntity<BoardResponseDto> updateBoard(
      @PathVariable Long boardId,
      @Valid @RequestBody BoardUpdateRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, boardId)) {
      throw new IllegalArgumentException("수정권한이 없습니다.");
    }

    BoardResponseDto responseDto = boardService.updateBoard(boardId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  // 게시글 삭제
  @DeleteMapping("/{boardId}")
  public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, boardId)) {
      throw new IllegalArgumentException("삭제 권한이 없습니다.");
    }

    boardService.deleteBoard(boardId);
    return ResponseEntity.noContent().build();
  }

  public boolean haveModifyAuthorization(User loginUser, Long boardId) {
    Long authorId = boardService.getAuthorIdByBoardId(boardId);
    return loginUser.getUserId().equals(authorId);
  }
}
