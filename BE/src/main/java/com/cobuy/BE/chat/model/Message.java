package com.cobuy.BE.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Message {
    private String roomId;
    private String messageId;
    private String userId;
    private String message;
    private String sendTime;
}
