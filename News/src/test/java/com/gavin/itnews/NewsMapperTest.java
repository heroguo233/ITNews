package com.gavin.itnews;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.mapper.NewsMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/11 15:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItNewsApplication.class)
public class NewsMapperTest {
    @Autowired
    NewsMapper newsMapper;

    @Test
    public void myTest1(){
        List<News> news = newsMapper.selectAllNews();
        for (News news1 : news) {
            System.out.println("news1 = " + news1);
        }
    }
    @Test
    public void myTest2(){
        News news = new News();
        news.setTitle("test");
        news.setLink("test");
        news.setImage("test");
        news.setCreatedDate(new Date());
        news.setCommentCount(0);
        news.setLikeCount(0);
        news.setUserId(4);
        int i = newsMapper.insertNews(news);
        Assert.assertTrue(i==1);
    }
}
