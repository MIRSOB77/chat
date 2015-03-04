package chat.messaging;

import chat.client.RawMessage;
import chat.messaging.ChatClientNotification;
import chat.messaging.ChatClientNotificationUserStatus;
import chat.repository.entity.ChatMessage;
import chat.service.MessageService;
import chat.service.exception.ChatException;
import chat.service.exception.UserManagementException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by mirsad on 27.02.15.
 */
@Controller
public class NotificationMessagingController {

    @Autowired
    MessageService messageService;

    @MessageMapping("/notifyUserStatus")
    @SendTo("/topic/userStatus")
    public ChatClientNotification notifyAboutUser(ChatClientNotificationUserStatus userStatus){
        return new ChatClientNotificationUserStatus("mickey", Boolean.TRUE);
    }

//    @MessageMapping("/notifyGlobal")
//    @SendTo("/topic/")
//    public ChatClientNotification notify(){
//        return new ChatClientNotificationUserMessage("johny", "Hello all of you!");
//    }

    @MessageMapping("/send")
    @SendTo("/topic/receiver")
    public ChatClientNotification sendMessage(RawMessage messageJson)
            throws IllegalAccessException, InvocationTargetException, UserManagementException, ChatException {

        ChatMessage chatMessage = messageService.transformMessage(messageJson);

        Long id = messageService.send(chatMessage);
        ChatClientNotification outgoingMsg = new ChatClientNotificationUserMessage(
                chatMessage.getSender().getNickname(),
                chatMessage.getText(),
                chatMessage.getReceiver().getNickname());

        return outgoingMsg;
    }





}
