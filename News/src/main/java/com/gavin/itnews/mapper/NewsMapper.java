package com.gavin.itnews.mapper;

import com.gavin.itnews.domain.News;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:14
 */
@Mapper
public interface NewsMapper {

    @Select("select id,title,link,image,like_count as likeCount,comment_count as commentCount,created_date as createdDate,user_id as userId from news")
    @ResultType(ArrayList.class)
    List<News> selectAllNews();

    @Insert("insert news(title,link,image,like_count,comment_count,created_date,user_id) values(#{news.title},#{news.link},#{news.image},#{news.likeCount},#{news.commentCount},#{news.createdDate},#{news.userId})")
    int insertNews(@Param("news") News news);

    @Update("update news set comment_count = comment_count + 1 where id = #{newsId}")
     int increaseCommentCountByNewsId(@Param("newsId")Integer newsId);

    @Select("select id,title,link,image,like_count as likeCount,comment_count as commentCount,created_date as createdDate,user_id as userId from news where id = #{newsId}")
    @ResultType(News.class)
    News selectNewsById(@Param("newsId") String newsId);


    @Update("update news set like_count = #{num} where id = #{newsId}")
    void updateLikeCountByNewsId(@Param("newsId")int newsId, @Param("num")int num);
}
