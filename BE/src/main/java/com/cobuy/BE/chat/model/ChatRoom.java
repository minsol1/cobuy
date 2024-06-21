package com.cobuy.BE.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatRoom {
    private String roomId;
    private String roomName;
    private String create_time;
    private int eventNo;//roomId랑 같은건데?
    private List<String> participants;
}