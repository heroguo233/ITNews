package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.mapper.NewsMapper;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ArrayList<ViewObject> showNews() {
        List<News> newsList = newsMapper.selectAllNews();
        ArrayList<ViewObject> vos = new ArrayList<ViewObject>();

        for (News news : newsList) {
            ViewObject viewObject = new ViewObject();
            Integer userId = news.getUserId();
            User user = userMapper.selectUserByUserId(userId);
            viewObject.set("news", news);
            viewObject.set("user", user);
            vos.add(viewObject);
        }
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

}
