package com.blogapp.photo.repositories;

import com.blogapp.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPhotoDao extends JpaRepository<Photo, Long> {
    List<Photo> findByPostId(Long thePostId);
}
