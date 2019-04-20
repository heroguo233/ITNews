package com.gavin.itnews.mapper;

import com.gavin.itnews.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Gavin
 * on 2019/4/10 21:24
 */
@Mapper
public interface UserMapper {

    // 这种方式判断是否为空，查询到了返回true  但是查询不到有异常
    @Select("select count(1) from user where name = #{name} limit 1")
    @ResultType(User.class)
    User usernameIsExist(@Param("name") String username);
    @Select("select id,name,password,salt,head_url as headUrl from user where name = #{name}")
    @ResultType(User.class)
    User selectUserByUsername(@Param("name") String username);
    @Select("select id,name,password,salt,head_url as headUrl from user where name = #{name} and password = #{password}")
    @ResultType(User.class)
    User findUserByUsernameAndPassword(@Param("name") String username, @Param("password") String password);
    @Insert("insert user (name,password,salt,head_url) values (#{name},#{password},#{salt},#{headUrl})")
    void insertUser(@Param("name") String username, @Param("password") String password, @Param("salt") String salt, @Param("headUrl") String head);
    @Select("select id,name,password,salt,head_url as headUrl from user where id = #{userId}")
    @ResultType(User.class)
    User selectUserByUserId(@Param("userId") Integer userId);

}
