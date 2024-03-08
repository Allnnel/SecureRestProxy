package org.example.service;


import org.example.exception.CustomException;
import org.example.model.User;

import java.util.List;


public interface UserService {

    List<User> findAll() throws CustomException;
    User findById(Long id) throws CustomException;
    void save(User user) throws CustomException;
    User findByUsername(String username) throws CustomException;
    void deleteByUsername(String username) throws CustomException;
    public void deleteById(Long id) throws CustomException;
}
