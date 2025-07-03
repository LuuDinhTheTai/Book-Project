package com.me.bookproject.repository;

import com.me.bookproject.entity.user.Account;
import com.me.bookproject.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {
  
  Account findByUsername(String username);
}
