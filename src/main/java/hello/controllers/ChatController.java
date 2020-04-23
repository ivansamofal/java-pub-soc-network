package hello.controllers;

import hello.dtos.InputMarkAsReadMessageDto;
import hello.dtos.OutputMarkAsReadMessageDto;
import hello.dtos.OutputMessage;
import hello.dtos.WsMessage;
import hello.services.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class ChatController {

    final private WebSocketService webSocketService;

    public ChatController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(WsMessage message) throws Exception {

        webSocketService.sendMessage(message);

        return new OutputMessage(message, message.getFrom(), message.getText());
    }

    @MessageMapping("/read")
    @SendTo("/topic/read")
    public OutputMarkAsReadMessageDto markAsRead(InputMarkAsReadMessageDto inputDto) {
        return webSocketService.markAsRead(inputDto);
    }
}
