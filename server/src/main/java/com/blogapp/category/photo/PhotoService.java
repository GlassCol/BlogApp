package com.blogapp.category.photo;

import com.blogapp.category.photo.dto.Photo;
import com.blogapp.category.photo.repositories.IPhotoDao;
import com.blogapp.category.photo.services.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService implements IPhotoService {

    private IPhotoDao photoDao;

    @Autowired
    PhotoService(IPhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public List<Photo> getPhotos() {
        return photoDao.findAll();
    }

}
