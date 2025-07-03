package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Resource;

public interface ResourceService {
  
  Resource createIfNotExist(Resource resource);
  Resource findByName(String name);
  boolean existsByName(String name);
}
