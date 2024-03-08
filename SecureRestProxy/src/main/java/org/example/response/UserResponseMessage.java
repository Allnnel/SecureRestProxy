package org.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.model.User;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseMessage extends ResponseMessage{
    @JsonProperty("users")
    private User[] userList;

    @JsonProperty("user")
    private User user;

    public UserResponseMessage(String status, String message, String code, User[] userList, User user) {
        super(status, message, code);
        this.userList = userList;
        this.user = user;
    }

    public User[] getUserList() {
        return userList;
    }

    public void setUserList(User[] userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
