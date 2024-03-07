package org.example.service;


import org.example.exception.CustomException;
import org.example.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll() throws CustomException;
    User findById(Long id) throws CustomException;
}
