package com.blogapp.services;

import com.blogapp.domains.Photo;
import com.blogapp.repositories.IPhotoDao;
import com.blogapp.services.interfaces.IPhotoService;
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
