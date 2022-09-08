package com.blogapp.post;

import com.blogapp.post.domain.PostDTO;
import com.blogapp.post.services.IPostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

    private final IPostService postService;

    @Autowired
    PostController(IPostService postService) {
        this.postService = postService;
    }


    @GetMapping(path = {"", "/"})
    @ResponseBody
    public ResponseEntity<Object> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "/id/{thePostId}")
    @ResponseBody
    public ResponseEntity<Object> getPostsById(@PathVariable Long thePostId)  {
        return postService.getPostById(thePostId);
    }

    @GetMapping(path = "/users/id/{theUserId}")
    @ResponseBody
    public ResponseEntity<Object> getPostByUserId(@PathVariable Long theUserId) {
       return postService.getPostsByUserId(theUserId);
    }

    @GetMapping(path = "/users/username/{theUsername}")
    @ResponseBody
    public ResponseEntity<Object> getPostByUsername(@PathVariable String theUsername) {
        return postService.getPostsByUsername(theUsername);
    }

    @GetMapping(path = "/{thePostId}/photos")
    @ResponseBody
    public ResponseEntity<Object> getPostPhotoById(@PathVariable Long thePostId) {
        return postService.getPhotoByPostId(thePostId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = {"", "/"})
    @ResponseBody
    public ResponseEntity<Object> addPost(@RequestBody PostDTO postDto) {
        return postService.addPost(postDto);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = {"", "/"})
    @ResponseBody
    public ResponseEntity<Object> updatePost(@RequestBody PostDTO postDto) {
        return postService.updatePost(postDto);
    }

    @DeleteMapping(path = {"", "/{thePostId}"})
    @ResponseBody
    public ResponseEntity<Object> deletePostById(@PathVariable Long thePostId) {
        return postService.deletePostById(thePostId);

    }

}
