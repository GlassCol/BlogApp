package com.blogapp.post.services;

import com.blogapp.ResponseHandler;
import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.services.IPhotoService;
import com.blogapp.post.domain.Post;
import com.blogapp.post.exceptions.PostNotFoundException;
import com.blogapp.post.domain.PostDTO;
import com.blogapp.post.repositories.IPostDao;
import com.blogapp.user.domain.User;
import com.blogapp.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PostService implements IPostService {

    private final IPostDao postDao;
    private final IPhotoService photoService;
    private final IUserService userService;

    @Autowired
    PostService(IPostDao postDao, IPhotoService photoService, IUserService userService) {
        this.postDao = postDao;
        this.photoService = photoService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Object> getPosts() {
        List<Post> posts = postDao.findAll();

        if (posts.isEmpty()) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(posts, HttpStatus.NO_CONTENT, "No posts found");
        }
        return ResponseHandler.response(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPostsByUserId(Long theUserId) {
        List<Post> posts =  postDao.findByUserId(theUserId);

        if (posts.isEmpty()) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(posts, HttpStatus.NOT_FOUND,
                    "No posts found for user "+theUserId);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPostsByUsername(String theUsername) {
        List<Post> posts =  postDao.findByUserUsername(theUsername);

        if (posts.isEmpty()) {
            log.info(new EntityNotFoundException().getMessage());
            return ResponseHandler.response(posts, HttpStatus.NOT_FOUND,
                    "No posts found for username "+theUsername);
        }
        return ResponseHandler.response(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPostById(Long thePostId) {
        Optional<Post> post = postDao.findById(thePostId);

        if (post.isPresent()) {
            return ResponseHandler.response(post.get(), HttpStatus.OK);
        }

        log.info(new PostNotFoundException(thePostId).getMessage());
        return ResponseHandler.response(post, HttpStatus.NOT_FOUND,
                new PostNotFoundException(thePostId).getMessage());
    }

    @Override
    public ResponseEntity<Object> getPhotoByPostId(Long thePostId) {
        List<Photo> photos = photoService.getPhotoByPostId(thePostId);

        if (photos.isEmpty()) {
            log.info(HttpStatus.NO_CONTENT.getReasonPhrase());
            return ResponseHandler.response(photos, HttpStatus.NOT_FOUND, "No content found");
        }
        return ResponseHandler.response(photos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addPost(PostDTO postDto) {
        // check if user is authorized to create a post
        Optional<User> user = userService.findByUsername(postDto.getUsername());

        // when user is found, then proceed to create a post
        if (user.isPresent()) {

            // get the user object - since the user object is a relationship field
            postDto.setUser(user.get());

            // map the dto object into a post object
            Post post = PostDTO.mapPostDtoToPost(postDto);

            // persist the post object to the db, return the post record having the post id
            Post savedPost = null;
            try {
               savedPost = postDao.save(post);
               // if a post is saved with a duplicate key, return exception
            } catch (DuplicateKeyException ex) {
                log.info(new EntityExistsException().getMessage());
                return ResponseHandler.response(
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Cannot save post: " + postDto.toString());
            }

            // when there are photos and after the post has been saved, then proceed to save photo(s)
            List<Photo> savedPhotos = photoService.addPhotosToPost(postDto.getPhotos(), savedPost);

            // add the saved photos list to the saved post object and return
            savedPost.setPhotos(savedPhotos);
            return ResponseHandler.response(savedPost, HttpStatus.CREATED, "The post was successfully created");
        }

        // when user is not found, adding a new post is prohibited
        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(new EntityNotFoundException().getMessage(), HttpStatus.FORBIDDEN, "User not authorized");

    }

    @Override
    public ResponseEntity<Object> updatePost(PostDTO postDto) {
        // check if user is authorized to update the post
        Optional<User> user = userService.findByUsername(postDto.getUsername());
        // check if the user is the owner of the post
        Optional<Post> existingPost = postDao.findById(postDto.getId());

        // when user and post are found &&
        // and is owner of the confirmed post, proceed to update
        if (user.isPresent() && existingPost.isPresent() &&
            user.get().getUsername().equals(existingPost.get().getUser().getUsername())) {

            // map the postDto to the post object
            Post post = PostDTO.mapPostDtoToPost(postDto);
            // transfer existing comment, photo, user objects to post object to update
            post.setComments(existingPost.get().getComments());
            post.setPhotos(existingPost.get().getPhotos());
            post.setUser(user.get());

            // update the post
            Post updatedPost = postDao.save(post);

            // return the updated post
            return ResponseHandler.response(updatedPost, HttpStatus.OK);
        }

        // when user or post is not found, then throw exception
        log.info(HttpStatus.FORBIDDEN.getReasonPhrase());
        return ResponseHandler.response(
                user.get().getUsername(),
                HttpStatus.FORBIDDEN,
                "User " + postDto.getUsername() + " is unauthorized");
    }

    @Override
    public ResponseEntity<Object> deletePostById(Long thePostId) {
        // may need to verify the user is the owner before allowing the post to be deleted

        // when the post exists, delete the post
        if (postDao.existsById(thePostId)) {
            postDao.deleteById(thePostId);
            return ResponseHandler.response(thePostId, HttpStatus.OK);
        }

        // when post is not found, then return not found
        log.info(new EntityNotFoundException().getMessage());
        return ResponseHandler.response(thePostId, HttpStatus.NOT_FOUND);

    }


}
