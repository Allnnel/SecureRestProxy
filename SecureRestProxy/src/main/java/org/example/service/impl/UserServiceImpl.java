package org.example.service.impl;

import java.util.List;
import java.util.Optional;
import org.example.exception.CustomException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<User> findAll() throws CustomException {
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
    if (repository.findByUsername(user.getUsername()).isPresent()) {
      throw new CustomException("USER_ALREADY_EXISTS", 2);
    }
    repository.save(user);
  }

  @Override
  public void update(User user) throws CustomException {
    Optional<User> optionalUser = repository.findByUsername(user.getUsername());
    if (optionalUser.isPresent()) {
      User updateUser = optionalUser.get();
      updateUser.setAddress(user.getAddress());
      updateUser.setName(user.getName());
      updateUser.setPhone(user.getPhone());
      updateUser.setEmail(user.getEmail());
      updateUser.setCompany(user.getCompany());
      updateUser.setWebsite(user.getWebsite());
      repository.save(updateUser);
    } else {
      repository.save(user);
    }
  }

  @Override
  public User findByUsername(String username) throws CustomException {
    Optional<User> user = repository.findByUsername(username);
    if (!user.isPresent()) {
      throw new CustomException("USER_NOT_FOUND", 1);
    }
    return user.get();
  }

  @Override
  public void deleteByUsername(String username) throws CustomException {
    findByUsername(username);
    repository.deleteByUsername(username);
  }

  @Override
  public void deleteById(Long id) throws CustomException {
    findById(id);
    repository.deleteById(id);
  }
}
