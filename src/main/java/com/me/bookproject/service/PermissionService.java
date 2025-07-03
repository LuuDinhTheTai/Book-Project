package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Permission;

import java.util.Collection;
import java.util.List;

public interface PermissionService {

  Permission createIfNotExist(Permission permission);
  Permission findByName(String name);
  boolean existsByName(String name);
  List<Permission> list();
}
