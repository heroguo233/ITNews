package com.gavin.itnews.utils;

/**
 * Created by Gavin
 * on 2019/4/15 22:21

 统一格式：让id小的在前，id大的在后面
 conversationId就是会话的标识，互相发送消息应当做同一个会话
 */

public class ConversationIdFormat {
    public static String format(String fromId,String toId){
        Integer fromIdInt = Integer.valueOf(fromId);
        Integer toIdInt = Integer.valueOf(toId);
        String  formatStr = fromIdInt <= toIdInt ?  fromIdInt+"_"+toIdInt : toIdInt + "_" + fromIdInt;
        return formatStr;
    }

    public static String format(int fromId,int toId){
        String  formatStr = fromId <= toId ?  fromId+"_"+toId : toId + "_" + fromId;
        return formatStr;
    }
}
