package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.Message;
import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.mapper.MessageMapper;
import com.gavin.itnews.mapper.UserMapper;
import com.gavin.itnews.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Gavin
 * on 2019/4/16 15:03
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<ViewObject> findAllMessageByUserId(String userId) {
        List<ViewObject> conversations = new ArrayList<>();
        List<Message> messages = messageMapper.selectMessagesByUserIdSql("%" + userId + "%");
        HashMap<String, ArrayList> hashMap = new HashMap<>();


        // 凡是发给 登录用户的信息全部拿出来了，现在开始做筛选 把conversation_id一样的归位一个
        // 用Map数据结构 方便筛选
        for (Message message : messages) {
            String conversationId = message.getConversationId();
            if (hashMap.get(conversationId) == null) {
                ArrayList<Message> messageArrayList = new ArrayList<>();
                hashMap.put(conversationId, messageArrayList);
            }
            ArrayList arrayList = hashMap.get(conversationId);
            arrayList.add(message);
        }

        Set<Map.Entry<String, ArrayList>> entrySet = hashMap.entrySet();
        for (Map.Entry<String, ArrayList> entry : entrySet) {
            ArrayList<Message> messageList = entry.getValue();
            // 这个ConversationId的消息总个数
            int count = messageList.size();
            ViewObject viewObject = new ViewObject();
            viewObject.set("count", count);
            //取最后一个 即 最新的消息放入
            viewObject.set("conversation", messageList.get(count - 1));
            //找发送者  随便拿一个元素 找他的fromId即可
            Message message = messageList.get(0);
            Integer fromId = message.getFromId();
            User user = userMapper.selectUserByUserId(fromId);
            viewObject.set("user", user);
            viewObject.set("conversationId",entry.getKey());
            conversations.add(viewObject);
        }


        return conversations;
    }

    @Override
    public List<ViewObject> findMessagesByConversationId(String conversationId) {
        List<ViewObject> messages = new ArrayList<>();
        List<Message> messagesList = messageMapper.selectMessagesByConversationId(conversationId);
        for (Message message : messagesList) {
            ViewObject viewObject = new ViewObject();
            Integer fromId = message.getFromId();
            Integer messageId = message.getId();
            User user = userMapper.selectUserByUserId(fromId);
            viewObject.set("userId", user.getId());
            viewObject.set("headUrl", user.getHeadUrl());
            viewObject.set("message", message);
            viewObject.set("messageId",messageId);
            //把ConversationId带回去 删除操作的时候有用
            viewObject.set("conversationId",conversationId);
            messages.add(viewObject);
        }


        return messages;
    }

    @Override
    public void deleteOneMsg(int messageId) {
        messageMapper.deleteOneMessageByMessageId(messageId);
    }

    @Override
    public void deleteMessages(String conversationId) {
        messageMapper.deleteMessagesByConversationId(conversationId);
    }
}
