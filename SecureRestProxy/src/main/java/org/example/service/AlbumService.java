package org.example.service;

import java.util.List;
import org.example.exception.CustomException;
import org.example.model.Album;

public interface AlbumService {
  void save(Album album) throws CustomException;

  void deleteById(Long id) throws CustomException;

  Album findById(Long id) throws CustomException;

  void deleteByUserId(Long userId) throws CustomException;

  List<Album> findByUserId(Long userId) throws CustomException;
}
