package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.event.EventProducer;
import com.gavin.itnews.event.EventType;
import com.gavin.itnews.mapper.NewsMapper;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.NewsService;
import com.gavin.itnews.utils.JedisUtils;
import com.gavin.itnews.utils.NewsSortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/10 16:14
 */
@Service
public class NewsServiceImpl implements NewsService {
    final static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    final static Object lock = new Object();

    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public News showNewsByIndex(String index,Integer loginId) {
      News news =newsMapper.selectNewsById(index);
        Jedis jedis = JedisUtils.getJedisFromPool();
        String likeNewsName = "like_news"+index;
        String dislikeNewsName = "dislike_news"+index;
        if(jedis.sismember(likeNewsName,loginId+"")){
            news.setExtData(1);
        }else if(jedis.sismember(dislikeNewsName,loginId+"")){
            news.setExtData(-1);
        }
        jedis.close();
        return news ;
    }

    //由于涉及到用户点赞问题，即要站在登录id视角来判断用户是否点赞，故需要传入一个登录id
    @Override
    public ArrayList<ViewObject> showNews(Integer loginId) {
        threadLocal.set(lock);
        int num = 0;
        HashMap<String, Integer> exData = new HashMap<>();
        Jedis jedis = JedisUtils.getJedisFromPool();
        synchronized (lock) {
            List<News> newsList = newsMapper.selectAllNews();


            NewsSortUtils.bubbleSort(newsList);
            ArrayList<ViewObject> vos = new ArrayList<ViewObject>();

            for (News news : newsList) {
                Integer newsId = news.getId();
                String likeNewsName = "like_news" + newsId;
                String dislikeNewsName = "dislike_news" + newsId;
                ViewObject viewObject = new ViewObject();
                Integer userId = news.getUserId();
                User user = userMapper.selectUserByUserId(userId);
                viewObject.set("news", news);
                viewObject.set("user", user);
/*
            已经更新了数据库，故不需要在这里拿redis数据了
           // 判断当前新闻的点赞数
            Long likeNum = jedis.scard(likeNewsName);
            Long dislikeNum = jedis.scard(dislikeNewsName);
            news.setLikeCount((int) (likeNum-dislikeNum));
            viewObject.set("news", news);
            viewObject.set("user", user);*/


//            下面代码是判断用户是否点赞、点踩该新闻
                //先让likeCount等于0
                viewObject.set("likeCount", 0);
                if (jedis.sismember(likeNewsName, loginId + "")) {
                    viewObject.set("likeCount", 1);
                } else if (jedis.sismember(dislikeNewsName, loginId + "")) {
                    viewObject.set("likeCount", -1);
                }

                vos.add(viewObject);
            }
            jedis.close();

            return vos;
        }
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
    public int likeNews(String userId, String newsId) {
        threadLocal.set(lock);
        int num = 0;
        HashMap<String, Integer> exData = new HashMap<>();
        Object lock = threadLocal.get();
        // 更新redis
        Jedis jedis = JedisUtils.getJedisFromPool();
        String likeNewsName = "like_news"+newsId;
        String dislikeNewsName = "dislike_news"+newsId;
        synchronized (lock) {
            jedis.sadd(likeNewsName, userId);
            jedis.srem(dislikeNewsName, userId);

        Long likeNum = jedis.scard(likeNewsName);
        Long dislikeNum = jedis.scard(dislikeNewsName);
        num = (int) (likeNum-dislikeNum);

        exData.put("num",num);
        jedis.close();
        // 0 是新闻点赞操作 1是评论 2 是点踩 3 是发私信
        // 点赞 targetId 管理员 然后以管理员身份发送消息
        EventProducer.fireEvent(EventType.LIKE,Integer.valueOf(userId),-1,Integer.valueOf(newsId),0,exData);

        return num;
        }
    }

    @Override
    public int dislikeNews(String userId, String newsId) {
        threadLocal.set(lock);
        Object lock = threadLocal.get();
        int num = 0;
        HashMap<String, Integer> exData = new HashMap<>();
        Jedis jedis = JedisUtils.getJedisFromPool();
        String likeNewsName = "like_news"+newsId;
        String dislikeNewsName = "dislike_news"+newsId;
        synchronized (lock){
            jedis.sadd(dislikeNewsName, userId);
            jedis.srem(likeNewsName,userId);
            Long likeNum = jedis.scard(likeNewsName);
            Long dislikeNum = jedis.scard(dislikeNewsName);
            num = (int) (likeNum-dislikeNum);

            exData.put("num",num);
            jedis.close();
            EventProducer.fireEvent(EventType.DISLIKE,Integer.valueOf(userId),-1,Integer.valueOf(newsId),2,exData);

        return num;
        }
    }

}
