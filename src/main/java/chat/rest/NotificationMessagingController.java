package chat.rest;

import chat.utils.ChatClientNotification;
import chat.utils.ChatClientNotificationUserStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by mirsad on 27.02.15.
 */
@Controller
public class NotificationMessagingController {
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

}
