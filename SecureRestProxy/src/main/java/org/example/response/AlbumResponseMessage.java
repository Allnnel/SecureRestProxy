package org.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Album;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumResponseMessage extends ResponseMessage{
    @JsonProperty("albums")
    private Album[] albums;

    @JsonProperty("album")
    private Album album;

    public AlbumResponseMessage(String status, String message, String code, Album[] albums, Album album) {
        super(status, message, code);
        this.albums = albums;
        this.album = album;
    }
}
