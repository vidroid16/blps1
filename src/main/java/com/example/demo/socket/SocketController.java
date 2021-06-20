package com.example.demo.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/queue/messages")
    public SocketMessage sendMessage(@Payload SocketMessage webSocketChatMessage) {
        return webSocketChatMessage;
    }
}