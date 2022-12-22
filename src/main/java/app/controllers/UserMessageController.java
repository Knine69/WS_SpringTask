package app.controllers;

import app.models.Greeting;
import app.models.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserMessageController {

    @MessageMapping("/topic/userMessage")
    @SendTo("/app/messenger")
    public Greeting sendBasicMessage(HelloMessage message) {
        return new Greeting(message.getName());
    }
}
