package chat.messaging;

import chat.utils.JsonResponseMap;

/**
 * Created by mirsad on 26.02.15.
 */
public class ChatClientNotification {
    protected ChatClientNotificationType type;
    private String message;


    public ChatClientNotification(){
    }

    public ChatClientNotification(String text){
        this.type = ChatClientNotificationType.SYSTEM_MESSAGE;
        this.message = text;
    }

    public JsonResponseMap getJsonStatus(){
        JsonResponseMap returnJson = new JsonResponseMap();
        returnJson.put("message", message);

        return returnJson;
    }
}
