package com.gavin.itnews;

import com.gavin.itnews.domain.User;
import com.gavin.itnews.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Gavin
 * on 2019/4/11 10:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItNewsApplication.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    /*
    @Select("select * from user where username = #{username}")
    @ResultType(User.class)
    User selectUserByUsername(@Param("username") String username);

     */
    @Test
    public void myTest1(){
        User user = userMapper.selectUserByUserId(4);
        System.out.println("user = " + user);
    }
    @Test
    public void myTest2(){
        userMapper.insertUser("gavin","123456");
    }
    @Test
    public void myTest3(){
        User gavin = userMapper.findUserByUsernameAndPassword("gavin", "123456");
        System.out.println("gavin = " + gavin);
    }
    @Test
    public void myTest4(){
        boolean gavin = userMapper.usernameIsExist("gavin12312")==1;
        Assert.assertFalse(gavin);
    }
    @Test
    public void myTest5(){
        User gavin = userMapper.selectUserByUsername("gavin");
        System.out.println("gavin = " + gavin);
    }

}
