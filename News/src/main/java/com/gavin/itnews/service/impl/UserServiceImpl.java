package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.User;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.UserService;
import com.gavin.itnews.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Gavin
 * on 2019/4/10 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     *
     * @param username
     * @param password
     * @return  0 : 登录成功
     *          1 : 用户名不存在
     *          2 ：密码错误
     */
    @Override
    public int login(String username, String password) {
        User userSalt = userMapper.selectUserByUsername(username);
        if(userSalt!=null){
            String salt = userSalt.getSalt();
            password = MD5Utils.getMD5(password,salt);
            User user = userMapper.findUserByUsernameAndPassword(username, password);
            if(user!=null){
                return 0;
            }else {
                //只有密码错误这一种情况了
                return 2;
            }
        }else {
            return 1;
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return
     *          1:用户名已经存在
     *          0：注册成功
     */
    @Override
    public int register(String username, String password) {
        User user = userMapper.usernameIsExist(username);
        if(user!=null){
            return 1;
        }else {
            String salt = MD5Utils.getSalt();
            String MD5Password = MD5Utils.getMD5(password, salt);
            String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
            userMapper.insertUser(username,MD5Password,salt,head);
            return 0;
        }
    }

    @Override
    public User findUserByUserId(Integer userId) {
        User user = userMapper.selectUserByUserId(userId);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        return user;
    }

}
