package com.sparta.boardproject.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(
    @NotBlank
    Long boardId,
    @NotBlank
    String commentContent
) {
}