package com.gavin.itnews.mapper;

import com.gavin.itnews.domain.User;

/**
 * Created by Gavin
 * on 2019/4/10 21:24
 */
public interface UserMapper {

    boolean usernameIsExist(String username);

    User selectUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    void insertUser(String username, String password);
}
