package org.example.repository;

import org.example.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(long id);
    void deleteById(long id);
}
