package com.zenve.chat.repository;

import com.zenve.chat.entity.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageInfoRepository extends JpaRepository<MessageInfo, Long> {

}
