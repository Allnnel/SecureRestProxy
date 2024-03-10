package org.example.service;

import java.util.List;
import org.example.exception.CustomException;
import org.example.model.Post;

public interface PostService {
  void save(Post post) throws CustomException;

  void deleteById(Long id) throws CustomException;

  Post findById(Long id) throws CustomException;

  void deleteByUserId(Long userId) throws CustomException;

  List<Post> findByUserId(Long userId) throws CustomException;
}
