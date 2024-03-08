package org.example.controller;

import org.example.model.Album;
import org.example.model.Post;
import org.example.model.User;
import org.example.exception.CustomException;
import org.example.response.AlbumResponseMessage;
import org.example.response.PostResponseMessage;
import org.example.response.ResponseMessage;
import org.example.response.UserResponseMessage;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ProxyController {
    private static final RestTemplate restTemplate = new RestTemplate();
    private final UserService userService;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @Autowired
    public ProxyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<ResponseMessage> getUsers() throws CustomException {
        try {
            String url = BASE_URL + "users/";
            ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, User[].class );
            User[] users = responseEntity.getBody();
            ResponseMessage response = new UserResponseMessage("Success", null, "200", users, null);
            return ResponseEntity.ok().body(response);
        } catch (HttpStatusCodeException e) {
            throw new CustomException(e.getMessage(), e.getRawStatusCode());
        }
    }

    @GetMapping("posts")
    public ResponseEntity<ResponseMessage> getPosts() throws CustomException {
        try {
            String url = BASE_URL + "posts/";
            ResponseEntity<Post[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Post[].class );
            Post[] posts = responseEntity.getBody();
            ResponseMessage response = new PostResponseMessage("Success", null, "200", posts, null);
            return ResponseEntity.ok().body(response);
        } catch (HttpStatusCodeException e) {
            throw new CustomException(e.getMessage(), e.getRawStatusCode());
        }
    }

    @GetMapping("albums")
    public ResponseEntity<ResponseMessage> getAlbums() throws CustomException {
        try {
            String url = BASE_URL + "albums/";
            ResponseEntity<Album[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Album[].class );
            Album[] albums = responseEntity.getBody();
            ResponseMessage response = new AlbumResponseMessage("Success", null, "200", albums, null);
            return ResponseEntity.ok().body(response);
        } catch (HttpStatusCodeException e) {
            throw new CustomException(e.getMessage(), e.getRawStatusCode());
        }
    }

}
