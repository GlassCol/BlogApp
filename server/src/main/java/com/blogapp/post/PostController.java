package com.blogapp.post;

import com.blogapp.ResponseHandler;
import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.domain.PhotoDTO;
import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDto;
import com.blogapp.post.services.IPostService;
import com.blogapp.photo.services.IPhotoService;
import com.blogapp.user.domain.User;
import com.blogapp.user.services.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

    private final IPostService postService;
    private final IPhotoService photoService;
    private final IUserService userService;

    @Autowired
    PostController(IPostService postService, IPhotoService photoService, IUserService userService) {
        this.postService = postService;
        this.photoService = photoService;
        this.userService = userService;
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

    // determine whether to use username or id
    @GetMapping(path = "/users/{theUserId}")
    public ResponseEntity<Object> getPostByUserId(@PathVariable Long theUserId) {
        List<Post> posts = postService.getPostsByUserId(theUserId);

        if (posts.isEmpty()) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(null,
                    HttpStatus.NOT_FOUND,
                    "No posts found for user "+theUserId);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);
    }

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
    public ResponseEntity<Object> getPostsById(@PathVariable Long theId)  {
        Optional<Post> post = postService.getPostById(theId);

        if (post.isPresent()) {
            return ResponseHandler.response(post, HttpStatus.OK);
        }
        log.info(new PostNotFoundException(theId).getMessage());
        return ResponseHandler.response(
                null,
                HttpStatus.NOT_FOUND,
                new PostNotFoundException(theId).getMessage());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = {"", "/"})
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
        // check if user is authorized to create a post
        Optional<User> user = userService.getUserByUsername(postDto.getUsername());

        // when user is found, then proceed to create a post
        if (user.isPresent()) {
            // get the user object - since the user object is a relationship field
            postDto.setUser(user.get());

            // map the dto object into a post object && persist the post object to the db, return the post with the post id
            Post post = PostDto.mapPostDtoToPost(postDto);
            Optional<Post> savedPost = postService.addPost(post);

            // when post is empty, i.e. cannot be saved, return an exception
            if (savedPost.isEmpty()) {
                log.info(new EntityExistsException().getMessage());
                return ResponseHandler.response(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // after the post has been saved, then proceed to save photo(s)
            // create a photo object from dto object && save the photo object to get the object with an id
            Photo photo = PhotoDTO.mapPostDtoToPhoto(postDto);
            photo.setPost(post);
            Optional<Photo> savedPhoto = photoService.addPhoto(photo);

            if (savedPhoto.isEmpty()) {
                log.info(new EntityExistsException().getMessage());
                return ResponseHandler.response(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            post.setPhotos(List.of(savedPhoto.get()));
            return ResponseHandler.response(savedPost.get(), HttpStatus.CREATED, "The post was successfully created");
        }

        // when user is not found, adding a new post is prohibited
        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(new EntityNotFoundException().getMessage(), HttpStatus.FORBIDDEN, "User not authorized");

    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = {"", "/"})
    public ResponseEntity<Object> updatePost(@RequestBody PostDto postDto) {
        // check if user is authorized to update the post
        Optional<User> user = userService.getUserByUsername(postDto.getUsername());
        // check if the user is the owner of the post
        Optional<Post> existingPost = postService.getPostById(postDto.getId());

        // when user and post are found
        if (user.isPresent() && existingPost.isPresent()) {
            // when user is owner of the confirmed post, proceed to update the post
            if (existingPost.get().getUser().getUsername()
                    .equals(user.get().getUsername())) {

                // map the postDto to the post object
                Post post = PostDto.mapPostDtoToPost(postDto);
                // transfer existing comment, photo, user objects to post object to update
                post.setComments(existingPost.get().getComments());
                post.setPhotos(existingPost.get().getPhotos());
                post.setUser(user.get());

                // update the post
                Optional<Post> updatedPost = postService.updatePost(post);

                if (updatedPost.isPresent()) {
                    return ResponseHandler.response(updatedPost.get(), HttpStatus.OK);
                }
            }

            // when user is not the owner, throw unauthorized
            log.info(HttpStatus.FORBIDDEN.getReasonPhrase());
            return ResponseHandler.response(null,
                    HttpStatus.FORBIDDEN, "User " + postDto.getUsername() + " is unauthorized");
        }

        // when user is not found, then throw exception
        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(null, HttpStatus.CONFLICT,  "User not found");
    }

    @DeleteMapping(path = {"", "/{theId}"})
    public ResponseEntity<Object> deletePostById(@PathVariable Long theId) {

        if ( postService.deletePostById(theId) ) {
            return ResponseHandler.response(null, HttpStatus.OK, "Resource deleted successfully");
        }
        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(null, HttpStatus.NOT_MODIFIED, "Resource not deleted");

    }

}
