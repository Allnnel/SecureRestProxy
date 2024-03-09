package org.example.repository;

import org.example.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findById(long id);
    void deleteById(long id);
}
