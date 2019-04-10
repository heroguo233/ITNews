package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.User;
import com.gavin.itnews.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Gavin
 * on 2019/4/10 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public User register(String username, String password) {
        return null;
    }

    @Override
    public User findUserByUserId(Integer userId) {
        User user = new User(1,"test","test","test","http://images.nowcoder.com/head/328t.png");
        return user;
    }

}
