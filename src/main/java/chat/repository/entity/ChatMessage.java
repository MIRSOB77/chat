package chat.repository.entity;

import javax.persistence.*;

/**
 * Created by Mirsad on 22.02.2015.
 */
@Entity
@Table(name = "messages")
public class ChatMessage {


    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Person sender;

    @ManyToOne
    Person receiver;

    String text;

    Boolean read;

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

