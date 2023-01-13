package app.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @Autowired
    private SimpMessagingTemplate template;

    @org.springframework.kafka.annotation.KafkaListener(topics = "userMessenger", groupId = "userMessagesID", autoStartup = "${listen.auto.start:true}")
    public void listenGroupMessages(String message) {
        this.template.convertAndSend("/topic/userMessages", message);
    }
}
