package com.sparta.boardproject.comment.repository;


import com.sparta.boardproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
