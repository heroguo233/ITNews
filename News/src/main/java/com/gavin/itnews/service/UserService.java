package com.gavin.itnews.service;

import com.gavin.itnews.domain.User;

/**
 * Created by Gavin
 * on 2019/4/10 16:16
 */
public interface UserService {
    int login(String username, String password);
    int register(String username, String password);
    User findUserByUserId(Integer userId);


    User findUserByUsername(String username);
}
