package org.example.service;

import org.example.exception.CustomException;
import org.example.model.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


// Сервис, который загружает информацию о пользователе для Spring Security
@Service
public class CustomUserDetailsService
//        implements UserDetailsService
{

//    private final UserService userService; // Репозиторий для доступа к данным пользователей
//
//    // Конструктор класса, внедряющий UserRepository в CustomUserDetailsService
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    // Метод, который загружает информацию о пользователе по его имени пользователя (логину)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Поиск пользователя в репозитории по имени пользователя (логину)
//        User user = null;
//        try {
//            user = userService.findByUsername(username);
//        } catch (CustomException e) {
//            throw new RuntimeException(e);
//        }
//        // Если пользователь не найден, генерируем исключение
//
//        // Преобразование данных о пользователе в формат UserDetails, используемый Spring Security
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), // Имя пользователя
//                user.getPassword(), // Пароль пользователя
//                getAuthorities(Collections.singleton(UserService.RoleEnum.valueOf(user.getRole()))) // Роли пользователя
//        );
//    }
//
//    // Вспомогательный метод, который преобразует роли пользователя в объекты GrantedAuthority
//    private Set<GrantedAuthority> getAuthorities(Set<UserService.RoleEnum> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.name()))
//                .collect(Collectors.toSet());
//    }

}
