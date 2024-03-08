package org.example.service;


import org.example.exception.CustomException;
import org.example.model.User;

import java.util.List;


public interface UserService {
    public enum RoleEnum {
        ROLE_ADMIN,
        ROLE_POSTS,
        ROLE_USERS,
        ROLE_ALBUMS
    }

    List<User> findAll() throws CustomException;

    User findById(Long id) throws CustomException;
    void save(User user) throws CustomException;
    void isValidRole(String role) throws CustomException;

    User findByUsername(String username) throws CustomException;
}
