package com.blogapp.photo;

import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.repositories.IPhotoDao;
import com.blogapp.photo.services.IPhotoService;
import com.blogapp.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService implements IPhotoService {

    private final IPhotoDao photoDao;

    @Autowired
    PhotoService(IPhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public List<Photo> getPhotos() {
        return photoDao.findAll();
    }

    @Override
    public List<Photo> addPhotosToPost(List<Photo> photos, Post post) {
        // add the post object to the photo object
        photos.forEach(photo -> photo.setPost(post));

        // persist the photo objects and return the photo record having the photo id
        return photos.stream().map(photoDao::save).toList();

    }

    @Override
    public List<Photo> getPhotoByPostId(Long thePostId) {
        return photoDao.findByPostId(thePostId);
    }

}
