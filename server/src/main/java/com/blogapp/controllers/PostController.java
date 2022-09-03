package com.blogapp.controllers;

import com.blogapp.domains.Photo;
import com.blogapp.domains.Post;
import com.blogapp.services.interfaces.IPhotoService;
import com.blogapp.services.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = {"/posts"}, produces = APPLICATION_JSON_VALUE )
public class PostController {

    private final IPostService postService;
    private final IPhotoService photoService;

    @Autowired
    PostController(IPostService postService, IPhotoService photoService) {
        this.postService = postService;
        this.photoService = photoService;
    }

    @GetMapping(path = {"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "/users/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostByUserId(@PathVariable Long theId) {
        return postService.getPostsByUserId(theId);
    }

    @GetMapping(path = "/categories/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByCategoryId(@PathVariable Long theId) {
        // todo this should call the category dao
        return postService.getPostsByCategoryId(theId);
    }

    @GetMapping(path = "/photos")
    @ResponseStatus(HttpStatus.OK)
    public List<Photo> getPostPhotos() {
        return photoService.getPhotos().stream().limit(4).toList();
    }

    @GetMapping(path = "/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostsById(@PathVariable Long theId) {
        return postService.getPostById(theId);
    }


    @PostMapping(path = "/", consumes = {APPLICATION_JSON_VALUE} )
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }

    @PutMapping(path = "/", consumes = {APPLICATION_JSON_VALUE} )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@RequestBody Post post) {
        postService.updatePost(post);
    }

    @DeleteMapping(path = "/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable Long theId) {
        postService.deletePostById(theId);
    }

}
