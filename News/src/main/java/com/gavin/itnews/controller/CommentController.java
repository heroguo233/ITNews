package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.service.CommentService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

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
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("code",1);
//        map.put("code","评论失败");
        String contextPath = request.getContextPath();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        comment.setUserId(userId);
        boolean flag= commentService.addComment(comment);
        if(flag){
//            map.put("code",0);
//            map.put("msg","评论成功");
            Integer newsId = comment.getNewsId();
            response.sendRedirect(contextPath+"/news/"+newsId);
        }
    }
}
