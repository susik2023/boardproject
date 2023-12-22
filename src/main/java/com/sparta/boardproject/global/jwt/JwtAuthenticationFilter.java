package com.sparta.boardproject.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.boardproject.user.dto.LogInRequestDto;
import com.sparta.boardproject.user.entity.UserRoleEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final JwtUtil jwtUtil;


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    log.info("로그인 시도");
    try {
      LogInRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LogInRequestDto.class);

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              requestDto.username(),
              requestDto.userPassword(),
              null
          )
      );
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    log.info("로그인 성공 및 JWT 생성");
    String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();//Authentication 인터페이스에 있는 getPrincipal 메서드 사용하고, userDetailsImpl로 형변환해서 get.Username사용
    UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();


    String token = jwtUtil.createToken(username, role);
    jwtUtil.addJwtToCookie(token, response);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    log.info("로그인 실패");
    response.setStatus(401);
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response.setContentType("application/json; charset=UTF-8");
//    response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
  }
}