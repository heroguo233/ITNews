package com.gavin.itnews.controller;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 11:52
 */
@Controller
public class MainController {
    @Autowired
    NewsService newsService;
    @RequestMapping("home")
    public String home(Model model, HttpServletRequest request, HttpSession httpSession, @RequestParam(value = "pop",defaultValue = "0")int pop){
        Integer loginId = 0;
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
            loginId =  user.getId();
            model.addAttribute("pop",1);
        }else {
            model.addAttribute("pop",0);
        }
        List<ViewObject> vos = newsService.showNews(loginId);
        model.addAttribute("vos",vos);
        return "home";
    }

}
