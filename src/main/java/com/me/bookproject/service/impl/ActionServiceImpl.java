package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Action;
import com.me.bookproject.repository.ActionRepository;
import com.me.bookproject.service.ActionService;
import com.me.bookproject.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl extends BaseServiceImpl<Action, Long> implements ActionService {
  
  private final ActionRepository repository;
  
  public ActionServiceImpl(ActionRepository repository) {
    super(repository);
    this.repository = repository;
  }
  
  @Override
  public Action createIfNotExist(Action action) {
    if (!repository.existsByName(action.getName())) {
      return create(action);
    }
    return null;
  }
  
  @Override
  public Action findByName(String name) {
    return repository.findByName(name);
  }
}
