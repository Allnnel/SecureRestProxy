package org.example.repository;

import org.example.exception.CustomException;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();

    void deleteByUsername(String username);
    void deleteById(long id);

}
