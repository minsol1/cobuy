package com.cobuy.BE.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatUser {

    String roomId;
    String userId;
}
