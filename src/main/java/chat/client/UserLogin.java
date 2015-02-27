package chat.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapping object for user login post action @see {@link chat.rest.UserManagementRestController#userLogin(UserLogin)}
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class UserLogin {
    @JsonProperty(required = true)
    private String nickname;

    @JsonProperty(required = true)
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
