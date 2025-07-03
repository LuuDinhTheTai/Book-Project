package com.me.bookproject.service;

import com.me.bookproject.dto.request.RegistrationRequest;
import com.me.bookproject.entity.user.Account;
import com.me.bookproject.service.base.BaserService;

public interface AccountService extends BaserService<Account, Long> {

  Account register(RegistrationRequest request);
  Account findByUsername(String username);
}
