package org.example.service.impl;

import org.example.exception.CustomException;
import org.example.model.Album;
import org.example.model.Post;
import org.example.repository.AlbumRepository;
import org.example.repository.PostRepository;
import org.example.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl  implements AlbumService {
    private final AlbumRepository repository;
    @Autowired
    public AlbumServiceImpl(AlbumRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Album album) throws CustomException {
        repository.save(album);
    }

}
