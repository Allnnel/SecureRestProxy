package org.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserResponseMessage extends ResponseMessage{
    @JsonProperty("users")
    private User[] users;

    @JsonProperty("user")
    private User user;

    public UserResponseMessage(String status, String message, String code, User[] users, User user) {
        super(status, message, code);
        this.users = users;
        this.user = user;
    }

}
