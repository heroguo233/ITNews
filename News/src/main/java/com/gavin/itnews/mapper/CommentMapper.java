package com.gavin.itnews.mapper;

import com.gavin.itnews.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/11 19:15
 */
@Mapper
public interface CommentMapper {

    @Select("select id,content,user_id as userId,news_id as newsId,news_type as newsType,created_date as createdDate,status from comment where news_id = #{newsId}")
    @ResultType(ArrayList.class)
    List<Comment> selectCommentsByNewsId(@Param("newsId") String newsId);


    @Insert("insert comment(content,user_id,news_id,news_type,created_date,status)" +
            " values(#{comment.content},#{comment.userId},#{comment.newsId},#{comment.newsType},#{comment.createdDate},#{comment.status})")
    int insertComment(@Param("comment") Comment comment);
}
