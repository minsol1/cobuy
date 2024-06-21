package com.cobuy.BE.chat.model.service;

import com.cobuy.BE.chat.model.ChatRoom;
import com.cobuy.BE.chat.model.Message;
import com.cobuy.BE.chat.model.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    @Override
    public void sendMessage(Message message) throws Exception {

        chatMapper.sendMessage(message);
    }

    @Override
    public void enterChatRoom(Map<String, String> params) throws Exception {
        chatMapper.enterChatRoom(params);
    }

    @Override
    public void createChatRoom(ChatRoom chatroom) throws Exception {
        chatMapper.createChatRoom(chatroom);
    }


}
