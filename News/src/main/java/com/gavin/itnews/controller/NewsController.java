package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.service.CommentService;
import com.gavin.itnews.service.NewsService;
import com.gavin.itnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:06
 */
@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @RequestMapping("/news/{index}")
    public String detail(Model model, @PathVariable String index){
        News news = newsService.showNewsByIndex(index);
        Integer userId = news.getUserId();
        User owner = userService.findUserByUserId(userId);
//        ViewObject vo = new ViewObject();
//        vo.set("news",news);
//        vo.set("owner",owner);
        model.addAttribute("news",news);
        model.addAttribute("owner",owner);
       List<ViewObject> commentsVos= getCommentsVo( index);
       model.addAttribute("comments",commentsVos);
        return "detail";
    }

    private List<ViewObject> getCommentsVo(String index) {
        List<ViewObject> commentsVo = new ArrayList<>();
        List<Comment> comments = commentService.findCommentsByNewsId(index);
        for (Comment comment : comments) {
            ViewObject vo = new ViewObject();
            Integer userId = comment.getUserId();
            User user = userService.findUserByUserId(userId);
            vo.set("comment",comment);
            vo.set("user",user);
        }
        return commentsVo;
    }
}
