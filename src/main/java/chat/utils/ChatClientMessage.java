package chat.utils;

/**
 * Created by mirsad on 26.02.15.
 */
public class ChatClientMessage {
    private Long id;
    private String message;

    public ChatClientMessage(Long id, String nickname, String text){
        this.id = id;
        this.setContent(nickname + ":" + text);

    }

    public void setContent(String msg){
        this.message = msg;
    }

    public String getContent(){
        return message;
    }
}
