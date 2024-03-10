package org.example.service.impl;

import java.util.List;
import java.util.Optional;
import org.example.exception.CustomException;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.example.service.PostService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  private final PostRepository repository;
  private final UserService userService;

  @Autowired
  public PostServiceImpl(PostRepository repository, UserService userService) {
    this.repository = repository;
    this.userService = userService;
  }

  @Override
  public void save(Post post) throws CustomException {
    userService.findById(post.getUserId());
    repository.save(post);
  }

  @Override
  public void deleteById(Long id) throws CustomException {
    findById(id);
    repository.deleteById(id);
  }

  @Override
  public Post findById(Long id) throws CustomException {
    Optional<Post> post = repository.findById(id);
    if (!post.isPresent()) {
      throw new CustomException("POST_NOT_FOUND", 1);
    }
    return post.get();
  }
  @Override
  public void deleteByUserId(Long userId) throws CustomException {
    List<Post> posts = findByUserId(userId);
    for (Post post : posts) {
      repository.delete(post);
    }
  }

  @Override
  public List<Post> findByUserId (Long userId) throws CustomException {
    List<Post> posts = repository.findByUserId(userId);
    if (posts.isEmpty()) {
      throw new CustomException("POST_NOT_FOUND", 1);
    }
    return posts;
  }
}
