package com.blogapp.post;

import com.blogapp.ResponseHandler;
import com.blogapp.category.photo.dto.Photo;
import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDto;
import com.blogapp.post.services.IPostService;
import com.blogapp.category.photo.services.IPhotoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = {"/posts"}, produces = APPLICATION_JSON_VALUE)
public class PostController {

    private final IPostService postService;
    private final IPhotoService photoService;
    private final ApplicationContext applicationContext;

    @Autowired
    PostController(IPostService postService, IPhotoService photoService, ApplicationContext applicationContext) {
        this.postService = postService;
        this.photoService = photoService;
        this.applicationContext = applicationContext;
    }


    @GetMapping(path = {"", "/"})
    public ResponseEntity<Object> getPosts() {
        List<Post> posts = postService.getPosts();

        if (posts.isEmpty()) {
            log.info("No content");
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);

    }

    @GetMapping(path = "/users/{theId}")
    public ResponseEntity<Object> getPostByUserId(@PathVariable Long theId) {
        List<Post> posts = postService.getPostsByUserId(theId);

        if (posts.isEmpty()) {
            log.info("No posts found for user "+theId);
            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "No posts found for user "+theId);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);

    }

    @GetMapping(path = "/categories/{theId}")
    public ResponseEntity<Object> getPostsByCategoryId(@PathVariable Long theId) {
        List<Post> posts = postService.getPostsByCategoryId(theId);

        if (posts.isEmpty()) {
            log.info("No categories found");
            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "No categories found for "+theId);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);

    }

    @GetMapping(path = "/photos")
    public ResponseEntity<Object> getPostPhotos() {
        List<Photo> photos = photoService.getPhotos().subList(0, 4);

        if (photos.isEmpty()) {
            log.info("No photos found");
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT, "No photos found");
        }
       return ResponseHandler.response(photos, HttpStatus.OK);

    }

    @GetMapping(path = "/{theId}")
    public ResponseEntity<Object>  getPostsById(@PathVariable Long theId)  {
        Post post = postService.getPostById(theId);

        if (Objects.isNull(post)) {
            log.info("No posts found for user "+theId);
            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "No posts found for user "+theId);
        }
        return ResponseHandler.response(post, HttpStatus.OK);

    }

    @PostMapping(path = "/")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
        Post post = applicationContext.getBean(Post.class);
        post = post.mapDtoToPost(postDto);

        try {
            postService.addPost(post);
            return ResponseHandler.response(null, HttpStatus.CREATED, "The post was successfully created");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            return ResponseHandler.response(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, "An error has occurred. Post could not be created");
        }

    }

    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> updatePost(@RequestBody PostDto postDto) {
        Post post = applicationContext.getBean(Post.class);
        post = post.mapDtoToPost(postDto);

        try {
            postService.updatePost(post);
            return ResponseHandler.response(null, HttpStatus.OK, "Resource updated successfully");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            return ResponseHandler.response(ex.getMessage(), HttpStatus.NOT_MODIFIED, HttpStatus.NOT_MODIFIED.getReasonPhrase());
        }

    }

    @DeleteMapping(path = "/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deletePostById(@PathVariable Long theId) {
        try {
            postService.deletePostById(theId);
            return ResponseHandler.response(null, HttpStatus.OK, "Resource deleted successfully");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            return ResponseHandler.response(ex.getMessage(), HttpStatus.NOT_MODIFIED,  HttpStatus.NOT_MODIFIED.getReasonPhrase());
        }

    }

}
