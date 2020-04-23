package hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {

    @RequestMapping("/socket/chat")
    public String chat() {
        return "chat";
    }
}
