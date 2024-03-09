package org.example.service.impl;

import java.util.List;
import java.util.Optional;
import org.example.exception.CustomException;
import org.example.model.Security;
import org.example.repository.SecurityRepository;
import org.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
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
    Optional<Security> securityOptional = repository.findByLogin(security.getLogin());
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
  public void delete(String login) throws CustomException {
    findByLogin(login);
    repository.deleteByLogin(login);
  }
}
