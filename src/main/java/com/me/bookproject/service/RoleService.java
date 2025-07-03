package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Role;
import com.me.bookproject.service.base.BaserService;

public interface RoleService extends BaserService<Role, Long> {
  
  boolean existsByName(String name);
}
