package com.zenve.chat.service;

import com.zenve.chat.entity.Group;
import com.zenve.chat.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Group getOneById(Long id) {
        return groupRepository.getOne(id);
    }

    public List<Group> getGroupsByUserId(Long userId) {
        return groupRepository.findAllByUserId(userId);
    }
}
