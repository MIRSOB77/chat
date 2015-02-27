package chat.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mirsad on 27.02.15.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class UserRegistration {

    @JsonProperty(required = true)
    private String firstName;

    @JsonProperty(required = true)
    private String lastName;


    @JsonProperty(required = true)
    private String emai;

    @JsonProperty(required = true)
    private String nickname;

    @JsonProperty(required = true)
    private String password;

}
