package com.me.bookproject.repository;

import com.me.bookproject.entity.RBAC0.Action;
import com.me.bookproject.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends BaseRepository<Action, Long> {
}
