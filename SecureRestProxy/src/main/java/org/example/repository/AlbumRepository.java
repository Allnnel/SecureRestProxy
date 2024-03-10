package org.example.repository;

import java.util.List;
import java.util.Optional;
import org.example.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AlbumRepository extends JpaRepository<Album, Long> {
  Optional<Album> findById(long id);

  void deleteById(long id);

  List<Album> findByUserId(long userId);
}
