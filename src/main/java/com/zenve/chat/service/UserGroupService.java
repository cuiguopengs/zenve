package com.zenve.chat.service;

import com.zenve.chat.entity.UserGroup;
import com.zenve.chat.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;

    public List<UserGroup> getAllByGroupId(Long groupId) {
        return userGroupRepository.findAllByGroupId(groupId);
    }
}
