package com.sparta.boardproject.board.entity;


import com.sparta.boardproject.user.entity.User;
import com.sparta.boardproject.comment.entity.Comment;
import com.sparta.boardproject.global.Timestamped;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long boardId;

  @Column(nullable = false)
  private String boardTitle;

  @Column(nullable = false)
  private String boardContent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<Comment> comments;

  public void update(Board updateBoard) {
    boardTitle = updateBoard.getBoardTitle();
    boardContent = updateBoard.getBoardContent();
  }
}
