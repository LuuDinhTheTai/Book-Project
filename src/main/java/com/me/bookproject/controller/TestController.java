package com.me.bookproject.controller;

import com.me.bookproject.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
  
  private final JwtUtil jwtUtil;
  
  @GetMapping("/user")
  public String test() {
    return "test";
  }
  
  @GetMapping("/admin")
  public String index() {
    return "admin";
  }
}
