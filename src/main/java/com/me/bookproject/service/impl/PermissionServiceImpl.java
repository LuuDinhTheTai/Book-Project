package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Permission;
import com.me.bookproject.repository.PermissionRepository;
import com.me.bookproject.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
  
  private final PermissionRepository repository;
  
  @Override
  public Permission createIfNotExist(Permission permission) {
    if (!existsByName(permission.getName())) {
      return repository.save(permission);
    }
    return null;
  }
  
  @Override
  public Permission findByName(String name) {
    return repository.findByName(name);
  }
  
  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }
  
  @Override
  public List<Permission> list() {
    return repository.findAll();
  }
}
