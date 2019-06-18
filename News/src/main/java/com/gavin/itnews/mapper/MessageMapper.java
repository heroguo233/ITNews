package com.gavin.itnews.mapper;

import com.gavin.itnews.domain.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Gavin
 * on 2019/4/13 17:00
 */
@Mapper
public interface MessageMapper {
    @Insert("insert message(from_id,to_id,content,created_date,has_read,conversation_id)  " +
            "values(#{message.fromId},#{message.toId},#{message.content},#{message.createdDate},#{message.hasRead},#{message.conversationId})")
    void insertMessage(@Param("message") Message message);

    // where 的语句执行顺序在前 故不能用as别名来代替
    // 带了单引号就不正确了 。。  估计连#{} 都当做参数来取了
    @Select("select id,from_id as fromId,to_id as toId, content,created_date as createdDate, has_read as hasRead,conversation_id as conversationId from message where conversation_id like #{conversationIdSql} ")
    @ResultType(List.class)
    List<Message> selectMessagesByUserIdSql(@Param("conversationIdSql") String userIdSql);


    @Select("select id,from_id as fromId,to_id as toId, content,created_date as createdDate, has_read as hasRead,conversation_id as conversationId from message where conversation_id = #{conversationId} ")
    @ResultType(List.class)
    List<Message> selectMessagesByConversationId(@Param("conversationId") String conversationId);

    @Delete("delete from message where id = #{messageId}")
    void deleteOneMessageByMessageId(@Param("messageId") int messageId);

    @Delete("delete from message where conversation_id=#{conversationId}")
    void deleteMessagesByConversationId(@Param("conversationId")String conversationId);
}
