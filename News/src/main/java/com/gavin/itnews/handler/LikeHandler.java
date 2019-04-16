package com.gavin.itnews.handler;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.event.Event;
import com.gavin.itnews.event.EventType;
import com.gavin.itnews.mapper.NewsMapper;
import com.gavin.itnews.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/15 22:11
 */
//
@Component
public class LikeHandler implements  EventHandler {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserMapper userMapper;
    List<EventType> processEventTypes ;
    {
        processEventTypes = new ArrayList<>();
        processEventTypes.add(EventType.LIKE);
    }
    @Override
    public List<EventType> getProcessEventType() {
        return processEventTypes;
    }

    @Override
    public void handleEvent(Event event) {
        int newsId = event.getItemID();
        // 1. 自增新闻赞数
        newsMapper.increaseLikeCountCountByNewsId(newsId);
        // 2. 记录用户的操作 这个新闻被用户点赞过 这里可能要新建一个表，在本地不打算做了 就用redis来记录这个操作

    }
}
