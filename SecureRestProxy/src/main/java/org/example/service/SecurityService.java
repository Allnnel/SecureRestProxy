package org.example.service;

import org.example.exception.CustomException;
import org.example.model.Security;
import org.example.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SecurityService {
    public enum RoleEnum {
        ROLE_ADMIN,
        ROLE_POSTS,
        ROLE_USERS,
        ROLE_ALBUMS
    }
    void save(Security security) throws CustomException;
    Security findByLoginAndRole(String login, String role) throws CustomException;
    List<Security> findAll() throws CustomException;
    void delete(String login, String role) throws CustomException;
}
