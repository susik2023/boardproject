package com.sparta.boardproject.user.dto;

import com.sparta.boardproject.user.entity.User;
import jakarta.validation.constraints.NotBlank;

public record UserSignUpRequestDto(

    @NotBlank
    String username,
    @NotBlank
    String userPassword,
    @NotBlank
    String validatepassword

) {
    public User toEntity(){
        return User.builder().username(username).userPassword(userPassword).build();
    }
}
