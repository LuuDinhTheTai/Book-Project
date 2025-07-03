package com.me.bookproject.controller;

import com.me.bookproject.dto.request.LoginRequest;
import com.me.bookproject.dto.request.RegistrationRequest;
import com.me.bookproject.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AccountController {
  
  private final AccountService accountService;
  
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }
  
  @GetMapping("registration")
  public String registration() {
    return "registration-form";
  }
  @PostMapping("register")
  public String register(RegistrationRequest request) {
    request.validate();
    accountService.register(request);
    return "OK";
  }
  
  @GetMapping("login")
  public String login() {
    return "login-form";
  }
  @PostMapping("login")
  public String login(LoginRequest request) {
    request.validate();
    accountService.login(request);
    return "OK";
  }
}
