package hello.services;

import hello.dtos.InputMarkAsReadMessageDto;
import hello.dtos.OutputMarkAsReadMessageDto;
import hello.dtos.WsMessage;
import hello.entities.Message;
import hello.entities.Row;
import hello.entities.User;
import hello.enums.StatusEnum;
import hello.factories.MessageFactory;
import hello.repositories.MessageRepository;
import hello.repositories.UserRepository;
import hello.singletons.RedissonBean;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WebSocketService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public WebSocketService(
            MessageRepository messageRepository,
            UserRepository userRepository
    ) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

//    @Transactional
    public void sendMessage(WsMessage message) {

        System.out.println("SOMETHING!!!");
        System.out.println(message.getId());
        System.out.println(message.getFrom());

        RedissonClient redisson = RedissonBean.getClient();
        RMap<String, User> map = redisson.getMap("userMap");

        User user = map.get("user".concat(message.getFrom()));
        if (user == null) {
            user = userRepository.findByUsername(message.getFrom());
            if (user != null) {
                map.put("user".concat(message.getFrom()), user);
            }
        }
        System.out.println("user");
        System.out.println(user);

        User userTo = userRepository.findById(2).orElse(null);

        Message messageEntity = MessageFactory.create(message, user, userTo);
        messageRepository.save(messageEntity);
    }

    public OutputMarkAsReadMessageDto markAsRead(InputMarkAsReadMessageDto inputDto) {
        messageRepository.markAsRead(1, 2);//todo get from dto
//        messageRepository.markAsRead();//todo get from dto
//        User userTo = userRepository.findById(2).orElse(null);
//        List<Message> messages = messageRepository.findAllByStatusAndAuthorUser_IdAndRecipientUser_Id(1, 1, 2);
//        System.out.println("FOUND MESSAGE: " + messages.size());
//        messages.forEach(m -> {
//            m.setStatus(2);
//        });
//        messageRepository.saveAll(messages);

        OutputMarkAsReadMessageDto dto = new OutputMarkAsReadMessageDto();
        dto.setFrom(inputDto.getFrom());
        dto.setTo(inputDto.getTo());

        return dto;
    }
}
