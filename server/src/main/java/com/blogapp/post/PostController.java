package com.blogapp.post;

import com.blogapp.ResponseHandler;
import com.blogapp.photo.domain.Photo;
import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDto;
import com.blogapp.post.services.IPostService;
import com.blogapp.photo.services.IPhotoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = {"/posts"}, produces = APPLICATION_JSON_VALUE)
public class PostController {

    private final IPostService postService;
    private final IPhotoService photoService;

    @Autowired
    PostController(IPostService postService, IPhotoService photoService) {
        this.postService = postService;
        this.photoService = photoService;
    }


    @GetMapping(path = {"", "/"})
    public ResponseEntity<Object> getPosts() {
        List<Post> posts = postService.getPosts();

        if (posts.isEmpty()) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);

    }

    @GetMapping(path = "/users/{theId}")
    public ResponseEntity<Object> getPostByUserId(@PathVariable Long theId) {
        List<Post> posts = postService.getPostsByUserId(theId);

        if (posts.isEmpty()) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "No posts found for user "+theId);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);

    }

//    @GetMapping(path = "/categories/{theId}")
//    public ResponseEntity<Object> getPostsByCategoryId(@PathVariable Long theId) {
//        List<Post> posts = postService.getPostsByCategoryId(theId);
//
//        if (posts.isEmpty()) {
//            log.info(new EntityNotFoundException().getMessage());
//            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "No categories found for "+theId);
//        }
//        return ResponseHandler.response(posts, HttpStatus.OK);
//
//    }

    @GetMapping(path = "/photos")
    public ResponseEntity<Object> getPostPhotos() {
        List<Photo> photos = photoService.getPhotos();

        if (photos.isEmpty()) {
            log.info("No photos found");
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT, "No photos found");
        }

        // adjust starting index of images to display when size greater than the window size
        int endIndex = 0;
        int windowSize = 5;
        if (photos.size() >= windowSize) {
            // temp implementation to get a random photo
            while (endIndex < windowSize) {
                int end = (int) (Math.random() * photos.size());
                if (end >= windowSize) {
                    endIndex = end;
                }
            }
            // set images to display
            photos = photos.subList(endIndex - windowSize, endIndex);
        }

        return ResponseHandler.response(photos, HttpStatus.OK);

    }

    @GetMapping(path = "/{theId}")
    public ResponseEntity<Object>  getPostsById(@PathVariable Long theId)  {
        Post post = postService.getPostById(theId);

        if (Objects.isNull(post)) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "No posts found for user "+theId);
        }
        return ResponseHandler.response(post, HttpStatus.OK);

    }

    @PostMapping(path = "/")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
        Post post = PostDto.mapPostDtoToPost(postDto);
        Optional<Post> savedPost = postService.addPost(post);

        if (savedPost.isPresent()) {
            return ResponseHandler.response(savedPost.get(), HttpStatus.CREATED, "The post was successfully created");
        }
        log.info(new EntityExistsException().getMessage());
        return ResponseHandler.response(new EntityExistsException().getMessage(), HttpStatus.CONFLICT);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Object> updatePost(@RequestBody PostDto postDto) {
        Post post = PostDto.mapPostDtoToPost(postDto);
        Optional<Post> updatedPost = postService.updatePost(post);

        if (updatedPost.isPresent()) {
            return ResponseHandler.response(updatedPost.get(), HttpStatus.OK);
        }

        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(null, HttpStatus.CONFLICT,  HttpStatus.CONFLICT.getReasonPhrase());
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<Object> deletePostById(@PathVariable Long theId) {

        if ( postService.deletePostById(theId) ) {
            return ResponseHandler.response(null, HttpStatus.OK, "Resource deleted successfully");
        }
        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(null, HttpStatus.NOT_MODIFIED, "Resource not deleted");

    }

}
