package com.gavin.itnews.handler;

import com.gavin.itnews.domain.Message;
import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.event.Event;
import com.gavin.itnews.event.EventType;
import com.gavin.itnews.mapper.MessageMapper;
import com.gavin.itnews.mapper.NewsMapper;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.utils.ConversationIdFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/15 22:10
 */

//消息处理器 给某人发送消息 例如 xx 点赞了你的新闻
@Component
public class MessageHandler implements EventHandler {
    final int sysId=888;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;
    // 这两个执行顺序？ 类先加载 还是代码块先执行？  类先加载

    // 静态代码块 > 构造代码块 > 局部代码块
    List<EventType> processEventTypes ;
    {
        processEventTypes = new ArrayList<>();
        processEventTypes.add(EventType.LIKE);
        processEventTypes.add(EventType.COMMENT);
        processEventTypes.add(EventType.PERSONAL_MESSAGE);
        //点踩就不通知用户了
    }
    @Override
    public List<EventType> getProcessEventType() {
        return processEventTypes;
    }

    @Override
    public void handleEvent(Event event) {
        int itemType = event.getItemType();
        switch (itemType){
            case 0:
                //点赞
                likeNewsMessage(event);
                break;
            case 1:
                //新闻评论
                commentNewsMessage(event);
                break;
            case 2:
                // 发私信
                sentPersonalMessage(event);
                break;
        }
    }

    private void commentNewsMessage(Event event) {
        int userId = event.getActorId();
        int newsId = event.getItemID();
        User user = userMapper.selectUserByUserId(userId);
        News news = newsMapper.selectNewsById(newsId + "");
        Integer targetId = news.getUserId();
        event.setTargetId(targetId);
        String name = user.getName();
        String content = "用户"+name+"评论了您发布的:"+ news.getTitle()+"URL:"+news.getLink();
        insertMessage(event,content,true);
    }

    private void sentPersonalMessage(Event event) {
        int userId = event.getActorId();
        User user = userMapper.selectUserByUserId(userId);
        //私人消息
        String content = (String) event.getExtData().get("content");
        insertMessage(event,content,false);
    }

    private void likeNewsMessage(Event event) {
        int userId = event.getActorId();
        int newsId = event.getItemID();
        User user = userMapper.selectUserByUserId(userId);
        News news = newsMapper.selectNewsById(newsId + "");
        String name = user.getName();
        Integer targetId = news.getUserId();
        event.setTargetId(targetId);
        String content = "用户"+name+"赞了您发布的:"+ news.getTitle()+"URL:"+news.getLink();

        insertMessage(event,content,true);
    }

    /**
     *
     * @param event   事件
     * @param content 消息内容
     * @param isAdmin 是否由管理员代发消息
     */
    private void insertMessage(Event event,String content,boolean isAdmin){
        Message message = new Message();
        message.setCreatedDate(new Date());
        message.setContent(content);
        // 插入消息的时候，targetId 和 ActorId需要变换一下，让系统管理员做ActorId  targetId做被点赞者
        if(isAdmin) {
            message.setFromId(sysId);
        }else {
            message.setFromId(event.getActorId());
        }
        // 无论是发私信，还是评论 toId 就是TargetId
        message.setToId(event.getTargetId());
        message.setHasRead(0);
        message.setConversationId(ConversationIdFormat.format(message.getFromId(),message.getToId()));
        messageMapper.insertMessage(message);
    }
}
