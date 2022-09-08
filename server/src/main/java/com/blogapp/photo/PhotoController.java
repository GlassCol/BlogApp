package com.blogapp.photo;

import com.blogapp.ResponseHandler;
import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.repositories.IPhotoDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/photos", produces = APPLICATION_JSON_VALUE)
public class PhotoController {

    private final IPhotoDao photoDao;

    PhotoController(IPhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @GetMapping(path = {"", "/"})
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity<Object> getPostPhotos() {
        List<Photo> photos = photoDao.findAll();

        if (photos.isEmpty()) {
            log.info("No photos found");
            return ResponseHandler.response(photos, HttpStatus.NO_CONTENT, "No photos found");
        }

        // adjust starting index of images to display when size greater than the window size
        int endIndex = 0;
        int windowSize = 5;
        if (photos.size() >= windowSize) {
            // temp implementation to get a random photo
            while (endIndex < windowSize) {
                int end = (int) (Math.random() * photos.size());
                if (end >= windowSize) {
                    endIndex = end;
                }
            }
            // set images to display
            photos = photos.subList(endIndex - windowSize, endIndex);
        }

        return ResponseHandler.response(photos, HttpStatus.OK);
    }

}
