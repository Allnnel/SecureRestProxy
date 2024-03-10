package org.example.service;

import org.example.exception.CustomException;
import org.example.model.Album;
import org.example.model.Post;

import java.util.List;

public interface PostService {
  void save(Post post) throws CustomException;
  void deleteById(Long id) throws CustomException;
  Post findById(Long id) throws CustomException;

  void deleteByUserId(Long userId) throws CustomException;
  List<Post> findByUserId (Long userId) throws CustomException;
}
