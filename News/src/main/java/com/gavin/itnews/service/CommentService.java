package com.gavin.itnews.service;

import com.gavin.itnews.domain.Comment;

import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:30
 */
public interface CommentService {
    List<Comment> findCommentsByNewsId(String newsId);

    boolean addComment(Integer userId, String newsId, String content);
}
