package com.me.bookproject.service;

import com.me.bookproject.dto.request.LoginRequest;
import com.me.bookproject.dto.request.RegistrationRequest;
import com.me.bookproject.dto.response.TokenResponse;
import com.me.bookproject.entity.user.Account;
import com.me.bookproject.service.base.BaserService;

public interface AccountService extends BaserService<Account, Long> {

  Account register(RegistrationRequest request);
  TokenResponse login(LoginRequest request);
  Account findByUsername(String username);
}
