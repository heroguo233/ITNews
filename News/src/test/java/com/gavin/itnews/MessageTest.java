package com.gavin.itnews;

import com.gavin.itnews.domain.Message;
import com.gavin.itnews.mapper.MessageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/16 15:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItNewsApplication.class)
public class MessageTest {
    @Autowired
    MessageMapper messageMapper;

    @Test
    public void myTest(){
        String userId = "13%";
        List<Message> messages = messageMapper.selectMessagesByUserIdSql(userId);
        for (Message message : messages) {
            System.out.println("message = " + message);
        }
    }
}
