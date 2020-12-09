package com.zenve.chat.repository;

import com.zenve.chat.entity.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {
}
