package com.blogapp.photo.services;

import com.blogapp.photo.domain.Photo;

import java.util.List;

public interface IPhotoService {
    List<Photo> getPhotos();
}
