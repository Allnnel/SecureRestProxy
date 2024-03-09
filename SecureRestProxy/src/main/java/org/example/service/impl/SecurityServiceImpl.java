package org.example.service.impl;

import org.example.exception.CustomException;
import org.example.model.Security;
import org.example.repository.SecurityRepository;
import org.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityServiceImpl  implements SecurityService {
    private final SecurityRepository repository;
    @Autowired
    public SecurityServiceImpl(SecurityRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Security> findAll() throws CustomException {
        List<Security> securityList = repository.findAll();
        if (securityList.isEmpty()) {
            throw new CustomException("SECURITY_NOT_FOUND", 1);
        }
        return securityList;
    }
    @Override
    public Security findByLoginAndRole(String login, String role) throws CustomException {
        isValidRole(role);
        Optional<Security> security = repository.findByLoginAndRole(login, role);
        if (!security.isPresent()) {
            throw new CustomException("SECURITY_NOT_FOUND", 1);
        }
        return security.get();
    }
    @Override
    public Security findByLogin(String login) throws CustomException {
        Optional<Security> security = repository.findByLogin(login);
        if (!security.isPresent()) {
            throw new CustomException("SECURITY_NOT_FOUND", 1);
        }
        return security.get();
    }
    @Override
    public void save(Security security) throws CustomException {
        isValidRole(security.getRole());
        Optional<Security> securityOptional = repository.findByLoginAndRole(security.getLogin(), security.getRole());
        if (securityOptional.isPresent()) {
            throw new CustomException("SECURITY_ALREADY_EXISTS", 1);
        }
        repository.save(security);

    }

    public void isValidRole(String role) throws CustomException {
        try {
            RoleEnum.valueOf(role);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CustomException("ROLE_NOT_FOUND", 3);
        }
    }
    @Override
    public void delete(String login, String role) throws CustomException {
        findByLoginAndRole(login, role);
        repository.deleteByLoginAndRole(login, role);
    }

}
