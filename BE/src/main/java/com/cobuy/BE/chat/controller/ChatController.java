package com.cobuy.BE.chat.controller;

import com.cobuy.BE.chat.model.ChatRoom;
import com.cobuy.BE.chat.model.ChatUser;
import com.cobuy.BE.chat.model.Message;
import com.cobuy.BE.chat.model.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "채팅 컨트롤러", description = "채팅 관련")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @Operation(summary = "채팅방 생성", description = "생성")
    @MessageMapping("/create")
    public void createChat(@ModelAttribute ChatRoom chatroom) throws Exception {

        log.info("채팅방{}이 생성되었습니다.", chatroom);

        //TODO: 채팅방 추가할 것
        chatService.createChatRoom(chatroom);
    }

    @Operation(summary = "채팅방 입장", description = "입장")
    @MessageMapping("/enter")
    public void enterChat(@ModelAttribute ChatUser chatuser) throws Exception {

        log.info("채팅방{}에 {}님이 입장하였습니다.", chatuser.getRoomId(), chatuser.getUserId());
        //TODO: 채팅방에 사람 추가할 것
        Map<String, String> map = new HashMap<>();
        map.put("roomId", chatuser.getRoomId());
        map.put("userId", chatuser.getUserId());
        chatService.enterChatRoom(map);

        //path:"sub/message/{roomId}구독중인 곳으로 message보냄
        messagingTemplate.convertAndSend("/sub/message/" + chatuser.getRoomId(), chatuser.getUserId()+"님이 입장하셨습니다.");
    }

    //url path:"pub/message"
    @Operation(summary = "메시지 전송", description = "전송")
    @MessageMapping("/message")
    public void sendMessage(@ModelAttribute Message message) throws Exception {
        log.info("sendMessage called with params: " + message);

        //TODO:DB에 채팅 올리기?
        message.setMessageId(UUID.randomUUID().toString());
        chatService.sendMessage(message);

        //path:"sub/message/{roomId}구독중인 곳으로 message보냄
        messagingTemplate.convertAndSend("/sub/message/" + message.getRoomId(), message);
    }

    @Operation(summary = "채팅방 퇴장", description = "채팅방 퇴장")
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        log.info("sessionId Disconnected: " + sessionId);
    }


}
