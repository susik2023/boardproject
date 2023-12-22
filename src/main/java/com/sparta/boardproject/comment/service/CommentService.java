package com.sparta.boardproject.comment.service;


import com.sparta.boardproject.board.entity.Board;
import com.sparta.boardproject.board.service.BoardService;
import com.sparta.boardproject.comment.dto.CommentRequestDTO;
import com.sparta.boardproject.comment.entity.Comment;
import com.sparta.boardproject.comment.repository.CommentRepository;
import com.sparta.boardproject.user.entity.User;
import com.sparta.boardproject.comment.dto.CommentResponseDTO;
import jakarta.transaction.Transactional;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final BoardService boardService;

  @Transactional
  public CommentResponseDTO createComment(CommentRequestDTO dto, User user) {
    Board board = boardService.findByBoardId(dto.boardId());
    Comment comment = Comment.createCommentof(dto.commentContent(),user,board);
    Comment saveComment = commentRepository.save(comment);

    return new CommentResponseDTO(saveComment);
  }

  @Transactional
  public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO,
      User user) {
    Comment comment = getUserComment(commentId, user);
    validateAuthorization(user, comment);
    comment.updateCommentContent(commentRequestDTO.commentContent());

    return new CommentResponseDTO(comment);
  }

  @Transactional
  public void deleteComment(Long commentId, User user) {
    Comment comment = getUserComment(commentId, user);
    validateAuthorization(user, comment);

    commentRepository.delete(comment);
  }

  private Comment getUserComment(Long commentId, User user) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
    return comment;
  }

  private void validateAuthorization(User user, Comment comment) {
    if (!user.getUserId().equals(comment.getUser().getUserId())) {
      throw new RejectedExecutionException();
    }
  }

}