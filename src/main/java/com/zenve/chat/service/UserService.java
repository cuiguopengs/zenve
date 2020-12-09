package com.zenve.chat.service;

import com.zenve.chat.entity.User;
import com.zenve.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {
        List<User> users =
                userRepository.findAllByUsernameAndPassword(username, password);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }


}
