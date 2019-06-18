package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public void addComment(Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        comment.setUserId(userId);
        boolean flag= commentService.addComment(comment);
        if(flag){
            Integer newsId = comment.getNewsId();
            response.sendRedirect(contextPath+"/news/"+newsId);
        }
    }

}
