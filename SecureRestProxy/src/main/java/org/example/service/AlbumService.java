package org.example.service;

import org.example.exception.CustomException;
import org.example.model.Album;
import org.example.model.Post;

import java.util.Optional;

public interface AlbumService {
    void save(Album album) throws CustomException;
    void deleteById(Long id) throws CustomException;
    Album findById(Long id) throws CustomException;
}
