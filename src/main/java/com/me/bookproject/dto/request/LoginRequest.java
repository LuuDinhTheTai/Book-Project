package com.me.bookproject.dto.request;

import com.me.bookproject.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
  
  private String username;
  private String password;
  
  public void validate() {
    if (username == null || username.isEmpty()) {
      throw new CustomException("Username cannot be empty");
    }
    if (password == null || password.isEmpty()) {
      throw new CustomException("Password cannot be empty");
    }
  }
}
