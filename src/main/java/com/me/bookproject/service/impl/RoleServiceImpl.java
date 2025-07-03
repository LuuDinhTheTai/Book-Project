package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Role;
import com.me.bookproject.repository.RoleRepository;
import com.me.bookproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  
  private final RoleRepository repository;
  
  @Override
  public Role createIfNotExist(Role role) {
    if (!existsByName(role.getName())) {
      return repository.save(role);
    }
    return null;
  }
  
  @Override
  public Role findByName(String name) {
    return repository.findByName(name);
  }
  
  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }
}
