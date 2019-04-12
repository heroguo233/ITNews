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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public String detail(Model model, @PathVariable String index) {
        News news = newsService.showNewsByIndex(index);
        Integer userId = news.getUserId();
        User owner = userService.findUserByUserId(userId);
        List<ViewObject> commentsVos = commentService.getCommentsVo(index);
        model.addAttribute("news", news);
        model.addAttribute("owner", owner);
        model.addAttribute("comments", commentsVos);
        return "detail";
    }

    @RequestMapping("like")
    @ResponseBody
    public HashMap<String, Object> likeNews(String newsId, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        String contextPath = request.getContextPath();
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        int num = newsService.likeNews(userId + "", newsId);

        map.put("code", 0);
        map.put("msg", num + "");

        return map;
    }

    @RequestMapping("dislike")
    @ResponseBody
    public HashMap<String, Object> dislikeNews(String newsId, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        String contextPath = request.getContextPath();
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        int num = newsService.dislikeNews(userId + "", newsId);

        map.put("code", 0);
        map.put("msg", num + "");

        return map;
    }


}
