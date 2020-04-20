package hello.factories;

import hello.dtos.WsMessage;
import hello.entities.Message;
import hello.entities.User;
import hello.enums.StatusEnum;

import java.time.LocalDateTime;

public class MessageFactory {

    public static Message create(WsMessage messageDto, User user, User userTo) {
        Message message = new Message();
        message.setCreatedAt(LocalDateTime.now());
        message.setStatus(StatusEnum.ACTIVE);
        message.setText(messageDto.getText());

        if (user != null) {
            System.out.println("USER: " + user.getId());
            message.setAuthorUser(user);
        }

        if (userTo != null) {
            message.setRecipientUser(userTo);
        }

        return message;
    }
}
