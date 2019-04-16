package com.gavin.itnews.handler;

import com.gavin.itnews.event.Event;
import com.gavin.itnews.event.EventType;
import com.gavin.itnews.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/15 22:11
 */
//
@Component
public class DislikeHandler implements  EventHandler {
    @Autowired
    NewsMapper newsMapper;
    List<EventType> processEventTypes ;
    {
        processEventTypes = new ArrayList<>();
        processEventTypes.add(EventType.DISLIKE);
    }
    @Override
    public List<EventType> getProcessEventType() {
        return processEventTypes;
    }

    @Override
    public void handleEvent(Event event) {
        int newsId = event.getItemID();
        // 1. 自减新闻赞数
        newsMapper.decreaseLikeCountCountByNewsId(newsId);
    }
}
