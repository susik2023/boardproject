package com.sparta.boardproject.user.dto;

import com.sparta.boardproject.user.entity.User;
import jakarta.validation.constraints.NotBlank;

public record LogInRequestDto(

    @NotBlank
    String username,
    @NotBlank
    String userPassword
) {
    public User toEntity(){
        return User.builder().username(username).userPassword(userPassword).build();
    }
}
