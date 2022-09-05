package com.blogapp.photo;

import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.repositories.IPhotoDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {

    @Mock
    private IPhotoDao photoDao;

    @InjectMocks
    private PhotoService photoService;

    @Test
    @DisplayName("Should return all photos")
    void getPhotosShouldReturnAllPhotos() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        photos.add(new Photo());
        when(photoDao.findAll()).thenReturn(photos);

        List<Photo> result = photoService.getPhotos();

        assertEquals(2, result.size());
        verify(photoDao, times(1)).findAll();
    }
}