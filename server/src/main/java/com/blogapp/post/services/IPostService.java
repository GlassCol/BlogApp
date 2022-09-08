package com.blogapp.post.services;

import com.blogapp.post.domain.PostDTO;
import org.springframework.http.ResponseEntity;

public interface IPostService {

    ResponseEntity<Object> getPosts();

    ResponseEntity<Object> getPostsByUserId(Long theUserId);

    ResponseEntity<Object> getPostsByUsername(String theUsername);

    ResponseEntity<Object> getPostById(Long thePostId);

    ResponseEntity<Object> getPhotoByPostId(Long thePostId);

    ResponseEntity<Object> addPost(PostDTO postDto);

    ResponseEntity<Object> updatePost(PostDTO postDto);

    ResponseEntity<Object> deletePostById(Long thePostId);

}
