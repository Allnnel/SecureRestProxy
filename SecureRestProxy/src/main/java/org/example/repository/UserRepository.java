package org.example.repository;

import java.util.List;
import java.util.Optional;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(long id);
  Optional<User> findByUsername(String username);
  List<User> findAll();
  void deleteByUsername(String username);
  void deleteById(long id);
}
