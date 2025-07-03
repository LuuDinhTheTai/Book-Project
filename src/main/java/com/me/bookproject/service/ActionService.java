package com.me.bookproject.service;

import com.me.bookproject.entity.RBAC0.Action;
import com.me.bookproject.service.base.BaserService;

public interface ActionService extends BaserService<Action, Long> {
  
  Action createIfNotExist(Action action);
  Action findByName(String name);
}
