package org.example.service.impl;

import java.util.List;
import java.util.Optional;
import org.example.exception.CustomException;
import org.example.model.Album;
import org.example.repository.AlbumRepository;
import org.example.service.AlbumService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
  private final AlbumRepository repository;
  private final UserService userService;

  @Autowired
  public AlbumServiceImpl(AlbumRepository repository, UserService userService) {

    this.repository = repository;
    this.userService = userService;
  }

  @Override
  public void save(Album album) throws CustomException {
    userService.findById(album.getUserId());
    repository.save(album);
  }

  @Override
  public void deleteById(Long id) throws CustomException {
    findById(id);
    repository.deleteById(id);
  }

  @Override
  public Album findById(Long id) throws CustomException {
    Optional<Album> album = repository.findById(id);
    if (!album.isPresent()) {
      throw new CustomException("ALBUM_NOT_FOUND", 1);
    }
    return album.get();
  }

  @Override
  public List<Album> findByUserId(Long userId) throws CustomException {
    List<Album> album = repository.findByUserId(userId);
    if (album.isEmpty()) {
      throw new CustomException("ALBUM_NOT_FOUND", 1);
    }
    return album;
  }

  @Override
  public void deleteByUserId(Long userId) throws CustomException {
    List<Album> albums = findByUserId(userId);
    for (Album album : albums) {
      repository.delete(album);
    }
  }
}
