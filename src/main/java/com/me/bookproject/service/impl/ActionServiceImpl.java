package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Action;
import com.me.bookproject.repository.ActionRepository;
import com.me.bookproject.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
  
  private final ActionRepository repository;
  
  @Override
  public Action createIfNotExist(Action action) {
    if (!repository.existsByName(action.getName())) {
      return repository.save(action);
    }
    return null;
  }
  
  @Override
  public Action findByName(String name) {
    return repository.findByName(name);
  }
  
  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }
}
