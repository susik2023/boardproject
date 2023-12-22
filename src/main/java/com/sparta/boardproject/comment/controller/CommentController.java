package com.sparta.boardproject.comment.controller;


import com.sparta.boardproject.comment.dto.CommentRequestDTO;
import com.sparta.boardproject.comment.dto.CommentResponseDTO;
import com.sparta.boardproject.comment.service.CommentService;
import com.sparta.boardproject.global.jwt.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentService CommentService;

  @PostMapping
  public ResponseEntity<CommentResponseDTO> postComment(
      @Valid @RequestBody CommentRequestDTO commentRequestDTO,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    CommentResponseDTO responseDTO = CommentService.createComment(commentRequestDTO,
        userDetails.getUser());

    return ResponseEntity.status(201).body(responseDTO);
  }

  @PatchMapping("/{commentId}")
  public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId,
      @Valid @RequestBody CommentRequestDTO commentRequestDTO,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    CommentResponseDTO responseDTO = CommentService.updateComment(commentId, commentRequestDTO,
        userDetails.getUser());
    return ResponseEntity.ok().body(responseDTO);

  }


  @DeleteMapping("/{commentId}")
  public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    CommentService.deleteComment(commentId, userDetails.getUser());
    return ResponseEntity.noContent().build();

  }

}