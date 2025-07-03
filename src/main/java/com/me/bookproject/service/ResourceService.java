package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Resource;
import com.me.bookproject.service.base.BaserService;

public interface ResourceService extends BaserService<Resource, Long> {
  
  Resource createIfNotExist(Resource resource);
  Resource findByName(String name);
}
