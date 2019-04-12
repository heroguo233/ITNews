package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.mapper.NewsMapper;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.NewsService;
import com.gavin.itnews.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:14
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public News showNewsByIndex(String index) {
      News news =newsMapper.selectNewsById(index);
        return news ;
    }

    //由于涉及到用户点赞问题，即要站在登录id视角来判断用户是否点赞，故需要传入一个登录id
    @Override
    public ArrayList<ViewObject> showNews(Integer loginId) {
        Jedis jedis = JedisUtils.getJedisFromPool();
        List<News> newsList = newsMapper.selectAllNews();
        ArrayList<ViewObject> vos = new ArrayList<ViewObject>();

        for (News news : newsList) {
            Integer newsId = news.getId();
            String likeNewsName = "like_news"+newsId;
            String dislikeNewsName = "dislike_news"+newsId;
            ViewObject viewObject = new ViewObject();
            Integer userId = news.getUserId();
            User user = userMapper.selectUserByUserId(userId);
            // 判断当前新闻的点赞数
            Long likeNum = jedis.scard(likeNewsName);
            Long dislikeNum = jedis.scard(dislikeNewsName);
            news.setLikeCount((int) (likeNum-dislikeNum));
            viewObject.set("news", news);
            viewObject.set("user", user);
//            下面代码是判断用户是否点赞、点踩该新闻
            //先让likeCount等于0
            viewObject.set("likeCount",0);
            if(jedis.sismember(likeNewsName,loginId+"")){
                viewObject.set("likeCount",1);
            }else if(jedis.sismember(dislikeNewsName,loginId+"")){
                viewObject.set("likeCount",-1);
            }

            vos.add(viewObject);
        }
        jedis.close();
        return vos;
    }

    @Override
    public boolean addNews(News news, User user) {
        //默认值
        news.setCreatedDate(new Date());
        news.setCommentCount(0);
        news.setLikeCount(0);
        news.setUserId(user.getId());
        int i = newsMapper.insertNews(news);
        return i==1;
    }

    @Override
    public boolean likeNews(String userId, String newsId) {
        Jedis jedis = JedisUtils.getJedisFromPool();
        String likeNewsName = "like_news"+newsId;
        String dislikeNewsName = "dislike_news"+newsId;
        Long sadd = jedis.sadd(likeNewsName, userId);
        jedis.srem(dislikeNewsName,userId);
        jedis.close();
        return sadd>=1;
    }

    @Override
    public boolean dislikeNews(String userId, String newsId) {
        Jedis jedis = JedisUtils.getJedisFromPool();
        String likeNewsName = "like_news"+newsId;
        String dislikeNewsName = "dislike_news"+newsId;
        Long sadd = jedis.sadd(dislikeNewsName, userId);
        jedis.srem(likeNewsName,userId);
        jedis.close();
        return sadd>=1;
    }

}
