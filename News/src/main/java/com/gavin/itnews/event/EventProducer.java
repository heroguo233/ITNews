package com.gavin.itnews.event;

import com.alibaba.fastjson.JSONObject;
import com.gavin.itnews.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * Created by Gavin
 * on 2019/4/15 21:38
 */
public class EventProducer {

    public static void fireEvent(EventType eventType, int actorId, int targetId, int itemId, int itemType, HashMap extData){

        Event event = new Event();
        event.setEventType(eventType);
        event.setActorId(actorId);
        event.setTargetId(targetId);
        event.setItemID(itemId);
        event.setItemType(itemType);
        event.setExtData(extData);


        // 把 event 转成Json  放到redis队列当中
        Jedis jedis = JedisUtils.getJedisFromPool();

        String eventJson = JSONObject.toJSONString(event);

        jedis.lpush("msgQueue",eventJson);
    }
}
