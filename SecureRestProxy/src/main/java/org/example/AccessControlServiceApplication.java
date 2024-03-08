package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AccessControlServiceApplication
//        implements CommandLineRunner
{
//    @Autowired
//    private UserService userService; // Подразумевается, что у вас есть сервис UserService, который может сохранять пользователей
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.role}")
    private String adminRole;

    public static void main(String[] args) {
        SpringApplication.run(AccessControlServiceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        // Хэширование пароля
//        String hashedPassword = passwordEncoder.encode(adminPassword);
//
//        // Создание объекта User для Spring Security
//        org.springframework.security.core.userdetails.User user =
//                new org.springframework.security.core.userdetails.User(adminUsername, hashedPassword,
//                        AuthorityUtils.createAuthorityList(adminRole));
//
//        // Сохранение пользователя
////        userService.saveUser(user);
//    }
//
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public PasswordEncoder applicationPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}

