package com.blogapp.photo.repositories;

import com.blogapp.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoDao extends JpaRepository<Photo, Long> {
}
