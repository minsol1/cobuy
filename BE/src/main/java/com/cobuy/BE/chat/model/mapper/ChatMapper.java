package com.cobuy.BE.chat.model.mapper;

import com.cobuy.BE.chat.model.ChatRoom;
import com.cobuy.BE.chat.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface ChatMapper {
    void sendMessage(Message message) throws SQLException;

    void enterChatRoom(Map<String, String> params) throws SQLException;

    void createChatRoom(ChatRoom chatroom) throws SQLException;
}
