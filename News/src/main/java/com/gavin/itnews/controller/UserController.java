package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.service.NewsService;
import com.gavin.itnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;
    @RequestMapping("reg")
    @ResponseBody
    public HashMap<String,Object> register(String username,String password
            ,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        int flag = userService.register(username, password);
        if(flag==0){
            User user = userService.findUserByUsername(username);
            map.put("code",0);
            map.put("msg","注册成功");
            httpServletRequest.getSession().setAttribute("user",user);
        }else {
            map.put("code",1);
            map.put("msgname","用户名已经被注册");
        }
        return map;
    }
//    模板化技术和Json没有冲突，why？
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(String username, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String contextPath = httpServletRequest.getContextPath();
        HashMap<String, Object> map = new HashMap<>();
        int flag = userService.login(username, password);

        /*
          0 : 登录成功
          1 : 用户名不存在
          2 ：密码错误
         *
         *          */
        switch (flag){
            case 0:
                User user = userService.findUserByUsername(username);
                httpServletRequest.getSession().setAttribute("user",user);
                map.put("code",0);
                map.put("msg","成功");
                break;
            case 1:
                map.put("code",1);
                map.put("msgname","用户名不存在");
                break;

            case 2:
                map.put("code",1);
                map.put("msgpsw","密码不正确");
                break;
        }
        return map;
    }
    @RequestMapping("logout")
    @ResponseBody
    public void logout(HttpSession httpSession,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
//        HashMap<String, Object> map = new HashMap<>();
        httpSession.invalidate();
//        map.put("code",0);
//        map.put("msg","退出成功");
//        return map;
        String contextPath = httpServletRequest.getContextPath();
        httpServletResponse.sendRedirect(contextPath+"/home");
    }
    @RequestMapping("/user/addNews")
    @ResponseBody
    public HashMap<String,Object> addNews(News news, HttpSession session){
        User user = (User) session.getAttribute("user");
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",1);
        map.put("msg","添加新闻失败");
        boolean flag = newsService.addNews(news,user);
        if(flag){
            map.put("code",0);
            map.put("msg","成功");
        }
        return map;
    }

    @RequestMapping("/user/{userId}")
    public String userDetail(@PathVariable String userId,Model model){
        User user = userService.findUserByUserId(Integer.valueOf(userId));
        model.addAttribute("user",user);
        return "personal";
    }

//           自己写重定向，会导致错误，前台已经帮我们做了跳转
//            httpServletResponse.sendRedirect(contextPath+"/home");
//            httpServletRequest.getSession().setAttribute("user",user);
//            return map;
//    @RequestMapping
//    public HashMap<String,Object> logout(HttpSession session){
//
//    }
}
