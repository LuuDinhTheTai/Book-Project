package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Role;

public interface RoleService {
  
  Role createIfNotExist(Role role);
  Role findByName(String name);
  boolean existsByName(String name);
}
