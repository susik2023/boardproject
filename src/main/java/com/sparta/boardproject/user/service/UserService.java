package com.sparta.boardproject.user.service;

import com.sparta.boardproject.user.dto.UserSignUpRequestDto;
import com.sparta.boardproject.user.entity.User;
import com.sparta.boardproject.user.dto.CommonResponseDto;
import com.sparta.boardproject.user.entity.UserRoleEnum;
import com.sparta.boardproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public CommonResponseDto signUp(UserSignUpRequestDto userSignUpRequestDto) {
    validateusername(userSignUpRequestDto);
    User user = User.builder()
        .username(userSignUpRequestDto.username())
        .userPassword(passwordEncoder.encode(userSignUpRequestDto.userPassword()))
        .role(UserRoleEnum.USER)
        .build();
    userRepository.save(user);
    return new CommonResponseDto("회원가입이 완료되었습니다.",200);
  }

  public void validateusername(UserSignUpRequestDto userSignUpRequestDto) {
    if(userRepository.findByUsername(userSignUpRequestDto.username()).isPresent()){
       throw new IllegalArgumentException("이미 존재하는 유저네임 입니다.");
    }
  }

}
