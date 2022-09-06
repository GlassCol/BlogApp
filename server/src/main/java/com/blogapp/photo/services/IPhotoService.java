package com.blogapp.photo.services;

import com.blogapp.photo.domain.Photo;

import java.util.List;
import java.util.Optional;

public interface IPhotoService {
    List<Photo> getPhotos();
    Optional<Photo> addPhoto(Photo photo);
}
