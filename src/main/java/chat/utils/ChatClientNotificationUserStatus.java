package chat.utils;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> getJsonStatus(){
        Map<String, Object> returnJson = new HashMap<String, Object>();
        returnJson.put("online", this.isOnline);

        return  returnJson;
    }
}
