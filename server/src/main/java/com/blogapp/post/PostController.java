package com.blogapp.post;

import com.blogapp.category.photo.dto.Photo;
import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDto;
import com.blogapp.post.services.IPostService;
import com.blogapp.category.photo.services.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
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

    // utilize Server Response to handle errors
    @GetMapping(path = {"", "/"})
    public ServerResponse getPosts() {
        List<Post> posts = postService.getPosts();

        if (!posts.isEmpty()) {
            return ServerResponse.ok().body(posts);
        } else {
            return ServerResponse.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = "/users/{theId}")
    public ResponseEntity<List<Post>> getPostByUserId(@PathVariable Long theId) {
        try {
            List<Post> posts = postService.getPostsByUserId(theId);

            if (posts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().headers(new HttpHeaders()).body(posts);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(path = "/categories/{theId}")
    public ResponseEntity<List<Post>> getPostsByCategoryId(@PathVariable Long theId) {
        try {
            List<Post> posts = postService.getPostsByCategoryId(theId);

            if (posts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(postService.getPostsByCategoryId(theId));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(path = "/photos")
    public ResponseEntity<List<Photo>> getPostPhotos() {
        try {
            List<Photo> photos = photoService.getPhotos().subList(0, 4);

            if (photos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(photos);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(path = "/{theId}")
    @ResponseBody
    public ResponseEntity<Post>  getPostsById(@PathVariable Long theId)  {
        Post post = postService.getPostById(theId);

        if (Objects.isNull(post)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @PostMapping(path = "/")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
        Post post = applicationContext.getBean(Post.class);
        post = post.mapDtoToPost(postDto);

        try {
            postService.addPost(post);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> updatePost(@RequestBody PostDto postDto) {
        Post post = applicationContext.getBean(Post.class);
        post = post.mapDtoToPost(postDto);

        if (Objects.nonNull(post)) {

            postService.updatePost(post);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(path = "/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deletePostById(@PathVariable Long theId) {
        try {
            postService.deletePostById(theId);

            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
