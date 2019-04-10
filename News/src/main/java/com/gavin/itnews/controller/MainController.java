package com.gavin.itnews.controller;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @RequestMapping("home")
    public String home(Model model){
        List<ViewObject> vos = getNews();

        model.addAttribute("vos",vos);
        return "home";
    }





    private List<ViewObject> getNews() {
        ArrayList<ViewObject> vos = new ArrayList<ViewObject>();

        for (int i = 0; i < 5; i++) {
            News news = new News(i,"test","test","test",i,i,new Date(),i);
            ViewObject viewObject = new ViewObject();
            viewObject.set("news",news);
            User user = new User(1,"test","test","test","http://images.nowcoder.com/head/328t.png");
            viewObject.set("user",user);
            vos.add(viewObject);
        }
        return vos;
    }
}
