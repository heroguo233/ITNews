package com.gavin.itnews;

import com.gavin.itnews.domain.User;
import com.gavin.itnews.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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

}
