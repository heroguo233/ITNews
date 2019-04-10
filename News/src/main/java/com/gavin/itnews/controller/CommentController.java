package com.gavin.itnews.controller;

import com.gavin.itnews.domain.User;
import com.gavin.itnews.service.CommentService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Gavin
 * on 2019/4/10 17:37
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping("/addComment")
    public void addComment(String newsId, String content, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        boolean flag= commentService.addComment(userId,newsId,content);
//     freemarker  这么写没问题，这样写相当于重定向
//        但是Json数据这样写是不可以的（根据博客来看）
//        return "redirect:/news/"+newsId;
        response.sendRedirect(contextPath+"/news/"+newsId);
    }
}
