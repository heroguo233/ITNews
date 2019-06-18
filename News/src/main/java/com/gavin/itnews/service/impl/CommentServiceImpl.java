package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.event.EventProducer;
import com.gavin.itnews.event.EventType;
import com.gavin.itnews.mapper.CommentMapper;
import com.gavin.itnews.mapper.NewsMapper;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.CommentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:30
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Comment> findCommentsByNewsId(String newsId) {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment(1, 1, 1, 1, new Date(), 1, "testComment");
        for (int i = 0; i < 5; i++) {
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public boolean addComment(Comment comment) {
//        目前不知道NewsType 、status是干嘛用的
        comment.setNewsType(0);
        comment.setCreatedDate(new Date());
        comment.setStatus(0);
        Integer newsId = comment.getNewsId();
        int x = commentMapper.insertComment(comment);
        int y = newsMapper.increaseCommentCountByNewsId(newsId);
        if(x==1&&y==1){
            // 谁的新闻 通知谁？ 这个让异步去查好了
            EventProducer.fireEvent(EventType.COMMENT,comment.getUserId(),-1,comment.getNewsId(),1,null);
        }

        return x==1&&y==1;
    }

    @Override
    public List<ViewObject> getCommentsVo(String index) {
        ArrayList<ViewObject> list = new ArrayList<>();
        List<Comment> comments = commentMapper.selectCommentsByNewsId(index);
        for (Comment comment : comments) {
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
//            查找评论者
            User user = userMapper.selectUserByUserId(comment.getUserId());
            vo.set("user",user);
            list.add(vo);
        }

        return list;
    }
}
