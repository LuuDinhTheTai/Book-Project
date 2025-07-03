package com.me.bookproject.service.impl;

import com.me.bookproject.constant.Constant;
import com.me.bookproject.dto.request.RegistrationRequest;
import com.me.bookproject.entity.user.Account;
import com.me.bookproject.repository.AccountRepository;
import com.me.bookproject.service.AccountService;
import com.me.bookproject.service.RoleService;
import com.me.bookproject.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService {
  
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;
  
  public AccountServiceImpl(AccountRepository repository,
                            PasswordEncoder passwordEncoder,
                            RoleService roleService) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
    this.roleService = roleService;
  }
  
  @Override
  public Account register(RegistrationRequest request) {
    log.info("(register) request: {}", request);
    Account account = new Account();
    account.setUsername(request.getUsername());
    account.setEmail(request.getEmail());
    account.setPassword(passwordEncoder.encode(request.getPassword()));
    account.getRoles().add(roleService.findByName(Constant.ROLE_USER));
    return create(account);
  }
}
