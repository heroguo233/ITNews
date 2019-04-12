package com.gavin.itnews.controller;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Date;
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
    public String home(Model model, @RequestParam(value = "pop",defaultValue = "0")int pop){
        List<ViewObject> vos = newsService.showNews();
        model.addAttribute("vos",vos);
        if(pop==1){
            model.addAttribute("pop",1);
        }
        return "home";
    }

}
