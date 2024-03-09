package org.example.service;

import org.example.exception.CustomException;
import org.example.model.Security;

import java.util.List;


public interface SecurityService {
    public enum RoleEnum {
        ADMIN,
        POSTS,
        USERS,
        ALBUMS
    }
    void save(Security security) throws CustomException;
    Security findByLoginAndRole(String login, String role) throws CustomException;
    List<Security> findAll() throws CustomException;
    void delete(String login, String role) throws CustomException;
    Security findByLogin(String login) throws CustomException;
}
