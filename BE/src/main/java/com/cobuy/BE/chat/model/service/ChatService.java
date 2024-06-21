package com.cobuy.BE.chat.model.service;

import com.cobuy.BE.chat.model.ChatRoom;
import com.cobuy.BE.chat.model.Message;

import java.util.Map;

public interface ChatService {

    void sendMessage(Message message) throws Exception;
    void enterChatRoom(Map<String,String> params) throws Exception;
    void createChatRoom(ChatRoom chatroom) throws Exception;
}
