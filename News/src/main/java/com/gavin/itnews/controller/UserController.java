package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:06
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("reg")
    public String register(String username,String password,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        User register = userService.register(username, password);
        if(register!=null){
            login(username,password,httpServletRequest,httpServletResponse);
            return null;
        }
        return "home";
    }

//    模板化技术和Json没有冲突，why？
    @RequestMapping("login")
    public HashMap<String, String> login(String username, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String contextPath = httpServletRequest.getContextPath();
        HashMap<String, String> map = new HashMap<>();
        int flag = userService.login(username, password);
        if(flag==1){
            User user=userService.findUserByUsername(username);
            httpServletRequest.getSession().setAttribute("user",user);
            map.put("code","0");
            map.put("msgname","登录成功");

            httpServletResponse.sendRedirect(contextPath+"/home");

        }
        return map;
    }
}
