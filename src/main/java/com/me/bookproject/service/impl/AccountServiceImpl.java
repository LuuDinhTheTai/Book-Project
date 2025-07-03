package com.me.bookproject.service.impl;

import com.me.bookproject.constant.Constant;
import com.me.bookproject.dto.request.LoginRequest;
import com.me.bookproject.dto.request.RegistrationRequest;
import com.me.bookproject.dto.response.TokenResponse;
import com.me.bookproject.entity.user.Account;
import com.me.bookproject.exception.CustomException;
import com.me.bookproject.repository.AccountRepository;
import com.me.bookproject.security.jwt.JwtUtil;
import com.me.bookproject.service.AccountService;
import com.me.bookproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  
  private final AccountRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;
  private final JwtUtil jwtUtil;
  
  @Override
  public Account create(Account account) {
    return repository.save(account);
  }
  
  @Override
  public Account register(RegistrationRequest request) {
    log.info("(register) request: {}", request);
    Account account = new Account();
    account.setUsername(request.getUsername());
    account.setEmail(request.getEmail());
    account.setPassword(passwordEncoder.encode(request.getPassword()));
    account.getRoles().add(roleService.findByName(Constant.ROLE.USER));
    return create(account);
  }
  
  @Override
  public TokenResponse login(LoginRequest request) {
    log.info("(login) request: {}", request);
    Account account = findByUsername(request.getUsername());
    
    if (account == null) {
      throw new CustomException("Username not found");
    }
    if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
      throw new CustomException("Invalid password");
    }
    
    String token = jwtUtil.generateToken(account);
    return new TokenResponse(token);
  }
  
  @Override
  public Account findByUsername(String username) {
    log.info("(find) username: {}", username);
    return repository.findByUsername(username);
  }
}
