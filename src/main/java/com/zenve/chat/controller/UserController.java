package com.zenve.chat.controller;

import com.zenve.chat.entity.User;
import com.zenve.chat.service.UserService;
import com.zenve.chat.vo.LoginVo;
import com.zenve.chat.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public UserVo getUserVo(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String appType) {
        return userService.login(new LoginVo(username, password, Integer.valueOf(appType)));
    }
}
