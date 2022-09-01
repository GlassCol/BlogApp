package com.blogapp.controllers;

import com.blogapp.domains.Post;
import com.blogapp.services.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final IPostService postService;

    @Autowired
    PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    List<Post> getPosts() {
        return postService.getAll();
    }

    // implement post services

}
