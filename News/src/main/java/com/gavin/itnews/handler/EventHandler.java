package com.gavin.itnews.handler;

import com.gavin.itnews.event.Event;
import com.gavin.itnews.event.EventType;

import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/15 20:40
 */
public interface EventHandler {
    //获取当前的事件处理器感兴趣(处理)的事件类型
    List<EventType> getProcessEventType();

    // 当前事件处理器对事件处理函数（回调）

    public void handleEvent(Event event);

}
