package com.sparta.boardproject.comment.entity;


import com.sparta.boardproject.board.entity.Board;
import com.sparta.boardproject.user.entity.User;
import com.sparta.boardproject.global.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long commentId;

  @Column(nullable = false)
  private String commentContent;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  @Column
  @CreatedDate
  private LocalDateTime createDate;

  public void updateCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }

  public static Comment createCommentof(String commentContent, User user, Board board) {

    return Comment.builder().commentContent(commentContent).board(board).user(user).build();
  }
}
