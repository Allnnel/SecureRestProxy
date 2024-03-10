package org.example.controller;

import org.example.controller.cache.UserCache;
import org.example.exception.CustomException;
import org.example.model.Album;
import org.example.model.Post;
import org.example.model.User;
import org.example.response.AlbumResponseMessage;
import org.example.response.PostResponseMessage;
import org.example.response.ResponseMessage;
import org.example.response.UserResponseMessage;
import org.example.service.AlbumService;
import org.example.service.PostService;
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
  private final PostService postService;
  private final AlbumService albumService;
  private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
  private final UserCache userCache;

  @Autowired
  public ProxyController(
      UserService userService, PostService postService, AlbumService albumService) {
    this.userService = userService;
    this.postService = postService;
    this.albumService = albumService;
    this.userCache = new UserCache();
  }

  @GetMapping("users")
  public ResponseEntity<ResponseMessage> getUsers() throws CustomException {
    try {
      String url = BASE_URL + "users/";
      ResponseEntity<User[]> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, null, User[].class);
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
      ResponseEntity<Post[]> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, null, Post[].class);
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
      ResponseEntity<Album[]> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, null, Album[].class);
      Album[] albums = responseEntity.getBody();
      ResponseMessage response = new AlbumResponseMessage("Success", null, "200", albums, null);
      return ResponseEntity.ok().body(response);
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), e.getRawStatusCode());
    }
  }

  @PostMapping("users")
  public ResponseEntity<ResponseMessage> postUsers(@RequestBody User user) throws CustomException {
    try {
      if (userCache.containsUser(user.getUsername())) {
        ResponseMessage response = new ResponseMessage("Fail", "USER_ALREADY_EXISTS", "500");
        return ResponseEntity.ok().body(response);
      }
      String url = BASE_URL + "users/";
      ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, user, User.class);
      User createdUser = responseEntity.getBody();
      userService.save(createdUser);
      userCache.addToCache(createdUser);
      ResponseMessage response =
          new UserResponseMessage("Success", null, "200", new User[] {createdUser}, null);
      return ResponseEntity.ok().body(response);
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), 1);
    }
  }

  @PostMapping("posts")
  public ResponseEntity<ResponseMessage> postPosts(@RequestBody Post post) throws CustomException {
    try {
      String url = BASE_URL + "posts/";
      ResponseEntity<Post> responseEntity = restTemplate.postForEntity(url, post, Post.class);
      Post createdPost = responseEntity.getBody();
      postService.save(createdPost);
      ResponseMessage response =
          new PostResponseMessage("Success", null, "200", new Post[] {createdPost}, null);
      return ResponseEntity.ok().body(response);
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), e.getRawStatusCode());
    }
  }

  @PostMapping("albums")
  public ResponseEntity<ResponseMessage> postAlbums(@RequestBody Album album)
      throws CustomException {
    try {
      String url = BASE_URL + "albums/";
      ResponseEntity<Album> responseEntity = restTemplate.postForEntity(url, album, Album.class);
      Album createdAlbum = responseEntity.getBody();
      albumService.save(createdAlbum);
      ResponseMessage response =
          new AlbumResponseMessage("Success", null, "200", new Album[] {createdAlbum}, null);
      return ResponseEntity.ok().body(response);
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), e.getRawStatusCode());
    }
  }

  @DeleteMapping("users")
  public ResponseEntity<ResponseMessage> deleteById(@RequestParam String username)
      throws CustomException {
    try {
      if (!userCache.containsUser(username)) {
        ResponseMessage response = new ResponseMessage("Fail", "USER_NOT_FOUND", "500");
        return ResponseEntity.ok().body(response);
      }
      String url = BASE_URL + "users/" + username;
      restTemplate.delete(url);
      userService.deleteByUsername(username);
      userCache.removeFromCache(username);
      try {
        postService.deleteByUserId(userService.findByUsername(username).getId());
        albumService.deleteByUserId(userService.findByUsername(username).getId());
      } catch (CustomException e) {
      }
      return ResponseEntity.ok().body(new ResponseMessage("Success", null, "200"));
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), e.getRawStatusCode());
    }
  }

  @DeleteMapping("posts")
  public ResponseEntity<ResponseMessage> deletePost(@RequestParam Long id) throws CustomException {
    try {
      String url = BASE_URL + "posts/" + id;
      restTemplate.delete(url);
      postService.deleteById(id);
      return ResponseEntity.ok().body(new ResponseMessage("Success", null, "200"));
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), e.getRawStatusCode());
    }
  }

  @DeleteMapping("albums")
  public ResponseEntity<ResponseMessage> deleteAlbum(@RequestParam Long id) throws CustomException {
    try {
      String url = BASE_URL + "albums/" + id;
      restTemplate.delete(url);
      albumService.deleteById(id);
      return ResponseEntity.ok().body(new ResponseMessage("Success", null, "200"));
    } catch (HttpStatusCodeException e) {
      throw new CustomException(e.getMessage(), e.getRawStatusCode());
    }
  }
}
