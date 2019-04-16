package com.gavin.itnews.service;

import com.gavin.itnews.domain.Message;
import com.gavin.itnews.domain.ViewObject;

import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/16 15:00
 */
public interface MessageService {
   List<ViewObject> findAllMessageByUserId(String userId);

   List<ViewObject> findMessagesByConversationId(String conversationId);

}
