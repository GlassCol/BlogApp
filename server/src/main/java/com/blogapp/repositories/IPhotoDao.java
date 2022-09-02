package com.blogapp.repositories;

import com.blogapp.domains.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoDao extends JpaRepository<Photo, Long> {
}
