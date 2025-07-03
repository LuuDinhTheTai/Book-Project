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
public class RegistrationRequest {
  
  private String username;
  private String email;
  private String password;
  private String cfPassword;
  
  public void validate() {
    if ( !password.equals(cfPassword)) {
      throw new CustomException("Password and confirm password must be same");
    }
  }
}
