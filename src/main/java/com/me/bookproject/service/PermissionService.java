package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Permission;
import com.me.bookproject.service.base.BaserService;

public interface PermissionService extends BaserService<Permission, Long> {

  Permission createIfNotExist(Permission permission);
  Permission findByName(String name);
}
