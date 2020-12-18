package com.zenve.chat.repository;

import com.zenve.chat.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findAllByGroupId(Long userId);
}
