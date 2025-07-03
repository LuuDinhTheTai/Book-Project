package com.me.bookproject.repository;

import com.me.bookproject.entity.RBAC0.Role;
import com.me.bookproject.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
  
  boolean existsByName(String name);
}
