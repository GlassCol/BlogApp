package com.blogapp.post.services;

import com.blogapp.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    List<Post> getPosts();

    List<Post> getPostsByUserId(Long theId);

    Optional<Post> getPostById(Long theId);

    Optional<Post> addPost(Post post);

    Optional<Post> updatePost(Post post);

    boolean deletePostById(Long theId);

}
