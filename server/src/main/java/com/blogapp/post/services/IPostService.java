package com.blogapp.post.services;

import com.blogapp.post.domain.Post;

import java.util.List;

public interface IPostService {

    List<Post> getPosts();

    List<Post> getPostsByUserId(Long theId);
    List<Post> getPostsByCategoryId(Long theId);
    Post getPostById(Long theId);

    void addPost(Post post);
    void updatePost(Post post);
    void deletePostById(Long theId);

}
