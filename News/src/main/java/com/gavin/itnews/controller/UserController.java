package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:06
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("reg")
    public String register(){

    }

    @RequestMapping("login")
    public String login(){
        userService.login()
    }
}
