package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Action;

public interface ActionService {
  
  Action createIfNotExist(Action action);
  Action findByName(String name);
  boolean existsByName(String name);
}
