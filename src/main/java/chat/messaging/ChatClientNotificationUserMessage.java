package chat.messaging;

import chat.utils.JsonResponseMap;

/**
 * Created by mirsad on 27.02.15.
 */
public class ChatClientNotificationUserMessage extends ChatClientNotification {
    private String receiverNickname;
    private String nickname;
    private String message;


    public ChatClientNotificationUserMessage(String nickname, String message, String receiverNickname){
        this.type = ChatClientNotificationType.MESSAGE_PRIVATE;
        this.nickname = nickname;
        this.receiverNickname = receiverNickname;
        this.message = message;
    }

    public ChatClientNotificationUserMessage(String nickname, String message){
        this.type = ChatClientNotificationType.MESSAGE_GLOBAL;
        this.nickname = nickname;
        this.message = message;
    }

    protected JsonResponseMap getReturnJson(){
        JsonResponseMap returnJson = new JsonResponseMap();
        returnJson.put("sender", this.nickname);
        returnJson.put("message", this.message);

        return returnJson;
    }
}
