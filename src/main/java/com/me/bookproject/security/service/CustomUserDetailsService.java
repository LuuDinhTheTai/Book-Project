package com.me.bookproject.security.service;

import com.me.bookproject.entity.user.Account;
import com.me.bookproject.exception.CustomException;
import com.me.bookproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
  
  @Autowired
  private AccountService accountService;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountService.findByUsername(username);
    if (account == null) {
      throw new CustomException("Account not found");
    }
    return new CustomUserDetails(account);
  }
}
