package org.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Post;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PostResponseMessage extends ResponseMessage{
    @JsonProperty("posts")
    private Post[] posts;

    @JsonProperty("post")
    private Post post;

    public PostResponseMessage(String status, String message, String code, Post[] posts, Post post) {
        super(status, message, code);
        this.posts = posts;
        this.post = post;
    }
}
