package com.gavin.itnews.controller;

import com.gavin.itnews.domain.Message;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;
import com.gavin.itnews.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/16 14:59
 */
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    @RequestMapping("/msg/list")
    public String msgList(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<ViewObject> conversations = messageService.findAllMessageByUserId(user.getId() + "");
        model.addAttribute("conversations",conversations);
        return "letter";
    }

    @RequestMapping("/msg/detail")
    public String msgDetail(Model model,String conversationId){
       List<ViewObject> messages=  messageService.findMessagesByConversationId(conversationId);
        model.addAttribute("messages",messages);
        return "letterDetail";
    }
}
