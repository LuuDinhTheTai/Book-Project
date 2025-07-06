package com.me.bookproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  
  @GetMapping("/user")
  public String test() {
    return "test";
  }
  
  @GetMapping("/admin")
  public String index(HttpServletRequest request) {
    System.out.println(request.getHeader("token"));
    return "admin";
  }
}
