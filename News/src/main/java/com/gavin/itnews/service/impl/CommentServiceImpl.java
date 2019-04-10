package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.Comment;
import com.gavin.itnews.service.CommentService;
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

    @Override
    public List<Comment> findCommentsByNewsId(String newsId) {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment(1, 1, 1, 1, new Date(), 1, "testComment");
        for (int i = 0; i < 5; i++) {
            comments.add(comment);
        }
        return comments;
    }
}
