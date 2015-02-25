package chat.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Mirsad on 23.02.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class RawMessage {
    @JsonProperty(required = true)
    String sender;

    @JsonProperty(required = true)
    String text;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
