package com.gavin.itnews.event;

import com.alibaba.fastjson.JSONObject;
import com.gavin.itnews.handler.EventHandler;
import com.gavin.itnews.utils.JedisUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Gavin
 * on 2019/4/15 21:43
 */
@Component
public class EventConsumer implements InitializingBean, ApplicationContextAware {

   private ApplicationContext applicationContext;

   private HashMap<EventType, List<EventHandler>> registerTable = new HashMap<>();
    @Override
    public void afterPropertiesSet() throws Exception {
        initRegisterTable();
        asyncOperation();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     *
     *  为什么我们要自己做一个注册表？  为了方便后面的调用，通过事件类型，去查找所有的处理器，进而执行处理器
     *  这个方法的目的是初始化注册表
     *
     *  由于Spring容器拿到的Handler是Processor  故下面代码有些复杂（for循环），但是注意自己想要做的操作，也不难理解
     *
     */
    private void initRegisterTable(){
        Map<String, EventHandler> eventHandlerMap = applicationContext.getBeansOfType(EventHandler.class);
        Set<Map.Entry<String, EventHandler>> eventHandlerSet = eventHandlerMap.entrySet();
        for (Map.Entry<String, EventHandler> entry : eventHandlerSet) {
            //并不care叫什么bean name
            EventHandler eventHandler = entry.getValue();
            List<EventType> eventTypeList = eventHandler.getProcessEventType();

            for (EventType eventType : eventTypeList) {

                if(!registerTable.containsKey(eventType)){
                    ArrayList<EventHandler> eventHandlers = new ArrayList<>();
                    registerTable.put(eventType,eventHandlers);
                }
                // 如果没有创建的话 和50行是一个list 故后面的不需要再put了
                List<EventHandler> eventHandlers = registerTable.get(eventType);
                eventHandlers.add(eventHandler);
            }
        }
    }

    /**
     * 异步操作
     */
    private void asyncOperation(){
        Jedis jedis = JedisUtils.getJedisFromPool();
        new Thread(){
            @Override
            public void run() {
                while(true){

                    // 这是由于brpop的API导致的返回一个list  第一个是String,并不在意
                    // b 不清楚是什么意思，但是使用之后会变成线程安全的操作
                    // r代表的 reverse    reversePop  当 入栈 lpush 而 出栈 rpop 数据结构就变成了队列
                    List<String> msgQueue = jedis.brpop(60*60, "msgQueue");

                    String eventJson = msgQueue.get(1);
                    Event event = JSONObject.parseObject(eventJson, Event.class);
                    EventType eventType = event.getEventType();
                    List<EventHandler> eventHandlers = registerTable.get(eventType);
                    for (EventHandler eventHandler : eventHandlers) {
                        //回调
                        eventHandler.handleEvent(event);
                    }
                }
            }
        }.start();
//        jedis.close();
    }
}
