package com.me.bookproject.service.impl;

import com.me.bookproject.entity.RBAC0.Resource;
import com.me.bookproject.repository.ResourceRepository;
import com.me.bookproject.service.ResourceService;
import com.me.bookproject.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService {
  
  private final ResourceRepository repository;
  
  public ResourceServiceImpl(ResourceRepository repository) {
    super(repository);
    this.repository = repository;
  }
  
  @Override
  public Resource createIfNotExist(Resource resource) {
    if (!repository.existsByName(resource.getName())) {
      return create(resource);
    }
    return null;
  }
  
  @Override
  public Resource findByName(String name) {
    return repository.findByName(name);
  }
}
