package org.example.service;

import org.example.exception.CustomException;
import org.example.model.Album;

public interface AlbumService {
  void save(Album album) throws CustomException;

  void deleteById(Long id) throws CustomException;

  Album findById(Long id) throws CustomException;
}
