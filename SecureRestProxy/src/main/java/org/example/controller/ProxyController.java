package org.example.controller;

import org.example.model.User;
import org.example.exception.CustomException;
import org.example.response.ResponseMessage;
import org.example.response.UserResponseMessage;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseMessage> getPost(@PathVariable("id") Long id) throws CustomException {
        User user = userService.findById(id);
        ResponseMessage response = new UserResponseMessage("Successes", null, "200", null, user);
        ExternalServiceController.checkUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @GetMapping("/users/")
//    public ResponseEntity<ResponseMessage> getPost() throws CustomException {
//        List<User> userList = userService.findAll();
//        ResponseMessage response = new UserResponseMessage("Successes", null, "200", userList, null);
//        ExternalServiceController.checkUsers(userList);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> getUsers() throws CustomException {
        try {
            String url = BASE_URL + "/users"; // Формируем URL удаленного сервера
            ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, User[].class );
            User[] userList = responseEntity.getBody(); // Получаем список пользователей из ответа
            ResponseMessage response = new UserResponseMessage("Success", null, "200", Arrays.asList(userList), null);
            return ResponseEntity.ok().body(response);
        } catch (HttpStatusCodeException e) {
            throw new CustomException(e.getMessage(), e.getRawStatusCode());
        }
    }


}
