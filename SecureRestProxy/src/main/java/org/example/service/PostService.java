package org.example.service;

import org.example.exception.CustomException;
import org.example.model.Post;

public interface PostService {
    void save(Post post) throws CustomException;
}
