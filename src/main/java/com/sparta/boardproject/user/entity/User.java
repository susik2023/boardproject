package com.sparta.boardproject.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  private String username;

  private String userPassword;

  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;

  @Builder
  public User(String username, String userPassword, UserRoleEnum role){
    this.username = username;
    this.userPassword = userPassword;
    this.role = role;
  }

}
