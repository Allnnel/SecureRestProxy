package org.example.repository;

import org.example.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SecurityRepository extends JpaRepository<Security, Long>{
    Optional<Security> findByLoginAndRole(String login, String role);
    List<Security> findAll();
    void deleteByLoginAndRole(String login, String role);
}
