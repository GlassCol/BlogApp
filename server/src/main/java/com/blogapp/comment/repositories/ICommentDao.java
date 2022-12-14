package com.blogapp.comment.repositories;

import com.blogapp.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentDao extends JpaRepository<Comment, Long> {
}
