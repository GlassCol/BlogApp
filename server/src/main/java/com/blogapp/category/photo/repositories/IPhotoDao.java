package com.blogapp.category.photo.repositories;

import com.blogapp.category.photo.dto.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoDao extends JpaRepository<Photo, Long> {
}
