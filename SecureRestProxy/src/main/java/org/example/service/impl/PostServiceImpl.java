package org.example.service.impl;

import org.example.exception.CustomException;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Post post) throws CustomException {
        repository.save(post);
    }
}
