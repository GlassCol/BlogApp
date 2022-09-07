package com.blogapp.photo;

import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.repositories.IPhotoDao;
import com.blogapp.photo.services.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Photo> addPhoto(Photo photo) {
        return Optional.of(photoDao.save(photo));
    }

    @Override
    public List<Photo> getPhotoByIdPost(Long thePostId) {
        return photoDao.findByPostId(thePostId);
    }

}
