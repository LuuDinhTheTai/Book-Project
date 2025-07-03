package com.me.bookproject.repository;

import com.me.bookproject.entity.RBAC0.Resource;
import com.me.bookproject.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, Long> {
}
