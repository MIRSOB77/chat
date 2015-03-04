package chat.repository.entity;

import javax.persistence.*;

/**
 * Created by Mirsad on 22.02.2015.
 */
@Entity
@Table(name = "messages")
public class ChatMessage {


    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    User sender;

    @ManyToOne
    User receiver;

    String text;

    public Boolean isNewMsg() {
        return newMsg;
    }

    public void setNewMsg(Boolean isNew) {
        this.newMsg = isNew;
    }

    Boolean newMsg = Boolean.FALSE;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

