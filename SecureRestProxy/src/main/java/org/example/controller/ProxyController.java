package org.example.controller;

import org.example.model.User;
import org.example.exception.CustomException;
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

//    @PostMapping("/users")
//    public ResponseEntity<ResponseMessage> postsUsers(@RequestParam String username,
//                                                      @RequestParam String password,
//                                                      @RequestParam String role) throws CustomException {
//        try {
//            User user = new User(username, password, role);
//            String url = BASE_URL + "/users";
//            ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, user, Void.class);
//            ResponseMessage response = new ResponseMessage("Success", null, "200");
//            return ResponseEntity.ok().body(response);
//        } catch (HttpStatusCodeException e) {
//            throw new CustomException(e.getMessage(), e.getRawStatusCode());
//        }
//    }

//    @PostMapping("/users/**")
//    public ResponseEntity<Object> proxyPostUsers(@RequestBody User requestBody) {
//        String url = BASE_URL + "/users/" + extractPathVariable();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
//        return restTemplate.postForEntity(url, requestEntity, Object.class);
//    }


}
