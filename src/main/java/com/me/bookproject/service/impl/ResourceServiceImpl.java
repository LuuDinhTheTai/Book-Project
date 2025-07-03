package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Resource;
import com.me.bookproject.repository.ResourceRepository;
import com.me.bookproject.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
  
  private final ResourceRepository repository;
  
  @Override
  public Resource createIfNotExist(Resource resource) {
    if (!existsByName(resource.getName())) {
      return repository.save(resource);
    }
    return null;
  }
  
  @Override
  public Resource findByName(String name) {
    return repository.findByName(name);
  }
  
  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }
}
