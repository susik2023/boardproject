package com.sparta.boardproject.user.controller;

import com.sparta.boardproject.user.dto.CommonResponseDto;
import com.sparta.boardproject.user.dto.UserSignUpRequestDto;
import com.sparta.boardproject.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUpRequestDto userSignUpRequestDto) {
    validatepassword(userSignUpRequestDto);
    CommonResponseDto commonResponseDto = userService.signUp(userSignUpRequestDto);
    return ResponseEntity.ok().body(commonResponseDto);
  }

  public void validatepassword(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
    if (!userSignUpRequestDto.userPassword().equals(userSignUpRequestDto.validatepassword())) {
      throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }
  }

  @GetMapping("/signup/usernamevalid")
  public ResponseEntity<?> validateusername(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
    try{
    userService.validateusername(userSignUpRequestDto);}
    catch(IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(new IllegalArgumentException());
    }
    return ResponseEntity.ok().body(new CommonResponseDto("가입 가능한 유저네임입니다.",200));
  }


}
