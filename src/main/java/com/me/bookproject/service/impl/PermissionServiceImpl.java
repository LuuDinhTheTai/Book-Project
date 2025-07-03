package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Permission;
import com.me.bookproject.repository.PermissionRepository;
import com.me.bookproject.service.PermissionService;
import com.me.bookproject.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService {
  
  private final PermissionRepository repository;
  
  public PermissionServiceImpl(PermissionRepository repository) {
    super(repository);
    this.repository = repository;
  }
  
  @Override
  public Permission createIfNotExist(Permission permission) {
    if (!repository.existsByName(permission.getName())) {
      return create(permission);
    }
    return null;
  }
  
  @Override
  public Permission findByName(String name) {
    return repository.findByName(name);
  }
}
