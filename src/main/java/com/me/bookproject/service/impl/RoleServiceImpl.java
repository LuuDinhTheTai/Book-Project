package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Role;
import com.me.bookproject.repository.RoleRepository;
import com.me.bookproject.repository.base.BaseRepository;
import com.me.bookproject.service.RoleService;
import com.me.bookproject.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {
  
  private final RoleRepository repository;
  
  public RoleServiceImpl(RoleRepository repository) {
    super(repository);
    this.repository = repository;
  }
  
  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }
}
