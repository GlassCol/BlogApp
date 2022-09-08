package com.blogapp.photo.services;

import com.blogapp.photo.domain.Photo;
import com.blogapp.post.domain.Post;

import java.util.List;

public interface IPhotoService {
    List<Photo> getPhotos();
    List<Photo> getPhotoByPostId(Long thePostId);

    List<Photo> addPhotosToPost(List<Photo> photos, Post post);
}
