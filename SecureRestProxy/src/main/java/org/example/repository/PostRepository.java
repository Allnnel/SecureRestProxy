package org.example.repository;

import java.util.List;
import java.util.Optional;
import org.example.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
  Optional<Post> findById(long id);

  void deleteById(long id);

  List<Post> findByUserId(long userId);
}
