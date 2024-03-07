package org.example.controller;

import org.example.exception.CustomException;
import org.example.model.User;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

import java.util.List;
public class ExternalServiceController {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String USER_URL = "users?";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public static void checkUsers(List<User> users) throws CustomException {
        try {
                String url =
                        BASE_URL + USER_URL
                                + "users="
                                + users;
                restTemplate.exchange(url, HttpMethod.GET, null, Void.class);
            } catch (HttpServerErrorException e) {
                throw new CustomException(e.getMessage(), 1);
            }
    }


    public static void checkUser(User user) throws CustomException {
        try {
            String url = BASE_URL + USER_URL;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Void.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new CustomException("Failed to check user", 2);
            }
        } catch (HttpServerErrorException e) {
            throw new CustomException(e.getMessage(), 2);
        }
    }
}
