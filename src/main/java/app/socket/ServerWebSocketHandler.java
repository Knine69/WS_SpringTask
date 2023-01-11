package app.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

@Component
public class ServerWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SimpMessagingTemplate template;

    @KafkaListener(topics = "userMessenger", groupId = "userMessagesID", autoStartup = "${listen.auto.start:true}")
    public void listenGroupMessages(String message) {
        this.template.convertAndSend("/topic/userMessages", message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        String response = String.format("%s", HtmlUtils.htmlEscape(request));
        System.out.println("Server sends: " + response);

        this.template.convertAndSend("/topic/userMessages", response);
        session.sendMessage(new TextMessage(response));
    }
}
