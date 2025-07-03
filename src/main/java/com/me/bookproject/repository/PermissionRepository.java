package com.me.bookproject.repository;

import com.me.bookproject.entity.RBAC0.Permission;
import com.me.bookproject.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> {
}
