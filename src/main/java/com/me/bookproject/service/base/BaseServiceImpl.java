package com.me.bookproject.service.base;

import com.me.bookproject.repository.base.BaseRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class BaseServiceImpl<T, ID> implements BaserService<T, ID> {
  
  private final BaseRepository<T, ID> repository;
  
  public BaseServiceImpl(BaseRepository<T, ID> repository) {
    this.repository = repository;
  }
  
  @Override
  public T create(T t) {
    log.info("(create) obj: {}", t);
    return repository.save(t);
  }
  
  @Override
  public T find(ID id) {
    log.info("(find) id: {}", id);
    return repository.findById(id).orElse(null);
  }
  
  @Override
  public List<T> list() {
    return repository.findAll();
  }
  
  @Override
  public T update(T t) {
    log.info("(update) obj: {}", t);
    return repository.save(t);
  }
  
  @Override
  public void delete(ID id) {
    log.info("(delete) id: {}", id);
    repository.deleteById(id);
  }
}
