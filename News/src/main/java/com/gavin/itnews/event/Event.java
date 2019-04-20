package com.gavin.itnews.event;

import java.util.HashMap;

/**
 * Created by Gavin
 * on 2019/4/15 20:29
 */
//事件模型
//别人需要什么东西
public class Event {
    //事件的类型
    EventType eventType; //0 : 点赞  1 点踩，2 回复

    //事件是谁触发  谁点赞
    int actorId;

    //事件的作用目标  点给谁的
    int targetId;


    // 下面两个是什么东西上的点赞

    int itemID  ; //哪个条目点赞

    int itemType ;  //那个具体的类型 新闻  评论

    //为了以后扩展
    HashMap<String,Object> extData;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public HashMap<String, Object> getExtData() {
        return extData;
    }

    public void setExtData(HashMap<String, Object> extData) {
        this.extData = extData;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", actorId=" + actorId +
                ", targetId=" + targetId +
                ", itemID=" + itemID +
                ", itemType=" + itemType +
                ", extData=" + extData +
                '}';
    }
}
