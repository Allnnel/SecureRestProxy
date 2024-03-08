package org.example.service.impl;

import org.example.exception.CustomException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() throws CustomException{
        List<User> userList = repository.findAll();
        if (userList.isEmpty()) {
            throw new CustomException("USER_NOT_FOUND", 1);
        }
        return userList;
    }

    @Override
    public User findById(Long id) throws CustomException {
        Optional<User> userOptional = repository.findById(id);
        if (!userOptional.isPresent()) {
            throw new CustomException("USER_NOT_FOUND", 1);
        }
        return userOptional.get();
    }

    @Override
    public void save(User user) throws CustomException {
        isValidRole(user.getRole());
        if (repository.findByUsernameAndRole(user.getUsername(), user.getRole()).isPresent()) {
            throw new CustomException("USER_ALREADY_EXISTS", 2);
        }
        repository.save(user);
    }

    @Override
    public void isValidRole(String role) throws CustomException {
        try {
            RoleEnum.valueOf(role);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CustomException("ROLE_NOT_FOUND", 3);
        }
    }
}