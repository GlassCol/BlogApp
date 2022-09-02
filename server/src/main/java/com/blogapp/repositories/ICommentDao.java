package com.blogapp.repositories;

import com.blogapp.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentDao extends JpaRepository<Comment, Long> {
}
