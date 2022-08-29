package com.blogapp.repositories;

import com.blogapp.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDao extends JpaRepository<Post, Long> {
}
