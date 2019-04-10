package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.User;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Gavin
 * on 2019/4/10 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    User user = new User(1,"test","test","test","http://images.nowcoder.com/head/328t.png");

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
        boolean flag = userMapper.usernameIsExist(username);
        if(flag){
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
        boolean flag = userMapper.usernameIsExist(username);
        if(flag){
            return 1;
        }else {
            userMapper.insertUser(username,password);
            return 0;
        }
    }

    @Override
    public User findUserByUserId(Integer userId) {

        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        return user;
    }

}
