package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AccessControlServiceApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AccessControlServiceApplication.class);
        app.run(args);
    }

}
