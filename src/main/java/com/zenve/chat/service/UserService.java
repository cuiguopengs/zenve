package com.zenve.chat.service;

import com.zenve.chat.entity.Group;
import com.zenve.chat.entity.User;
import com.zenve.chat.repository.GroupRepository;
import com.zenve.chat.repository.UserRepository;
import com.zenve.chat.vo.LoginVo;
import com.zenve.chat.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupService groupService;

    public UserVo login(LoginVo loginVo) {
        List<User> users =
                userRepository.findAllByUsernameAndPassword(loginVo.getUsername(), loginVo.getPassword());
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        User user = users.get(0);
        UserVo userVo = new UserVo();
        userVo.setUsername(user.getUsername());
        userVo.setId(user.getId());
        List<Group> groups = groupService.getGroupsByUserId(user.getId());
        userVo.setGroups(groups);
        return userVo;
    }

    public User getOneById(Long id) {
        return userRepository.getOne(id);
    }


}
