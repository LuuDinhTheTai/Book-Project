package com.me.bookproject.controller;

import com.me.bookproject.dto.request.LoginRequest;
import com.me.bookproject.dto.request.RegistrationRequest;
import com.me.bookproject.service.AccountService;
import com.me.bookproject.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {
  
  private final AccountService accountService;
  private final CookieUtil cookieUtil;
  
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
  public String login(@Valid LoginRequest request,
                      BindingResult bindingResult,
                      Model model,
                      HttpServletResponse response) {
    request.validate();
    
    if (bindingResult.hasErrors()) {
      model.addAttribute("errors", bindingResult.getAllErrors());
      return "login-form";
    }
    
    String token = accountService.login(request).getToken();
    model.addAttribute("message", token);
    
    response.addCookie(cookieUtil.createTokenCookie("token", token));
    return "OK";
  }
}
