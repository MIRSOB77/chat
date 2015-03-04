package chat.messaging;

import chat.utils.JsonResponseMap;

/**
 * Created by mirsad on 27.02.15.
 */
public class ChatClientNotificationUserStatus extends ChatClientNotification {

    protected String nickname;
    protected Boolean isOnline;

    public ChatClientNotificationUserStatus(String nickname, Boolean online){
        super();
        this.nickname = nickname;
        this.type = ChatClientNotificationType.USER_STATUS;
        this.isOnline = online;

    }

    public JsonResponseMap getJsonStatus(){
        JsonResponseMap returnJson = new JsonResponseMap();
        returnJson.put("online", this.isOnline);

        return  returnJson;
    }
}
