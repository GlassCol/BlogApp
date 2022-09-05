package com.blogapp.post;

import com.blogapp.photo.domain.Photo;
import com.blogapp.photo.services.IPhotoService;
import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDto;
import com.blogapp.post.services.IPostService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PostController tests")
class PostControllerTest {

    @Mock
    private IPostService postService;

    @Mock
    private IPhotoService photoService;

    @InjectMocks
    private PostController postController;


    // TEST GET POSTS ALL
    @Test
    @DisplayName("Should return a list of posts when there are posts in the database")
    void getPostsWhenThereArePostsInTheDatabase() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test title");
        post.setBody("Test body");

        when(postService.getPosts()).thenReturn(List.of(post));

        ResponseEntity<Object> response = postController.getPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when there are no posts in the database")
    void getPostsWhenThereAreNoPostsInTheDatabase() {
        when(postService.getPosts()).thenReturn(List.of());

        ResponseEntity<Object> response = postController.getPosts();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // TEST GET POST BY USER / POSTER ID
    @Test
    @DisplayName("Should return a list of posts when the user id is valid")
    void getPostByUserIdWhenUserIdIsValidThenReturnListOfPosts() {
        Long userId = 1L;
        Post post = new Post();
        post.setId(1L);
        post.setTitle("title");
        post.setBody("body");
        when(postService.getPostsByUserId(userId)).thenReturn(List.of(post));

        ResponseEntity<Object> response = postController.getPostByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when the user id is invalid")
    void getPostByUserIdWhenUserIdIsInvalidThenReturnEmptyList() {
        Long userId = 1L;
        when(postService.getPostsByUserId(userId)).thenReturn(List.of());

        ResponseEntity<Object> response = postController.getPostByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // TEST GETS POSTS BY THE CATEGORY ID
//    @Test
//    @DisplayName("Should return a list of posts when the category id is valid")
//    @Disabled
//    void getPostsByCategoryIdWhenCategoryIdIsValidThenReturnListOfPosts() {
//        Post post = new Post();
//        post.setId(1L);
//        post.setTitle("title");
//        post.setBody("body");
//
//        when(postService.getPostsByCategoryId(any())).thenReturn(List.of(post));
//
//        ResponseEntity<Object> response = postController.getPostsByCategoryId(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//    }
//
//    @Test
//    @DisplayName("Should return an empty list when the category id is invalid")
//    @Disabled
//    void getPostsByCategoryIdWhenCategoryIdIsInvalidThenReturnEmptyList() {
//        when(postService.getPostsByCategoryId(any())).thenReturn(List.of());
//
//        ResponseEntity<Object> response = postController.getPostsByCategoryId(1L);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNotNull(response.getBody());
//    }

    // TEST GET PHOTOS
    @Test
    @DisplayName("Should return a list of photos when there are photos in the database")
    void getPostPhotosWhenThereArePhotosInTheDatabaseThenReturnListOfPhotos() {
        List<Photo> photos = List.of(new Photo());

        when(photoService.getPhotos()).thenReturn(photos);
        ResponseEntity<Object> response = postController.getPostPhotos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when there are no photos in the database")
    void getPostPhotosWhenThereAreNoPhotosInTheDatabaseThenReturnEmptyList() {
        when(photoService.getPhotos()).thenReturn(List.of());

        ResponseEntity<Object> response = postController.getPostPhotos();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // TEST GET POST BY THE POST ID
    @Test
    @DisplayName("Should return a post when the id is valid")
    void getPostsByIdWhenIdIsValid() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("title");
        post.setBody("body");

        when(postService.getPostById(any())).thenReturn(post);

        ResponseEntity<Object> response = postController.getPostsById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return null when the id is invalid")
    void getPostsByIdWhenIdIsInvalid() {
        when(postService.getPostById(any())).thenReturn(null);
        ResponseEntity<Object> response = postController.getPostsById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // TEST ADD POST
    @Test
    @DisplayName("Should save the post when the post is valid")
    void addPostWhenPostIsValid() {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("title");
        postDto.setBody("body");

        Post post = new Post();
        post.setId(1L);
        post.setTitle("title");
        post.setBody("body");

        when(postService.addPost(any(Post.class))).thenReturn(Optional.of(post));

        ResponseEntity<Object> response = postController.addPost(postDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Should throw an exception when the post is invalid")
    void addPostWhenPostIsInvalidThenThrowException() {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("title");
        postDto.setBody("body");

        when(postService.addPost(any(Post.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> response = postController.addPost(postDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    // TEST UPDATE POST
    @Test
    @DisplayName("Should return the updated post when the post exists")
    void updatePostWhenPostExistsThenReturnUpdatedPost() {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("title");
        postDto.setBody("body");

        Post post = new Post();
        post.setId(1L);
        post.setTitle("title");
        post.setBody("body");

        when(postService.updatePost(any(Post.class))).thenReturn(Optional.of(post));

        ResponseEntity<Object> response = postController.updatePost(postDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return null when the post does not exist")
    void updatePostWhenPostDoesNotExistThenReturnNull() {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("title");
        postDto.setBody("body");

        when(postService.updatePost(any(Post.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> response = postController.updatePost(postDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    // TEST DELETE POST
    @Test
    @DisplayName("Should return true when the post is deleted")
    void deletePostByIdWhenPostIsDeletedThenReturnTrue() {
        Long id = 1L;
        when(postService.deletePostById(id)).thenReturn(true);

        ResponseEntity<Object> response = postController.deletePostById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return false when the post is not deleted")
    void deletePostByIdWhenPostIsNotDeletedThenReturnFalse() {
        Long id = 1L;
        when(postService.deletePostById(id)).thenReturn(false);

        ResponseEntity<Object> response = postController.deletePostById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_MODIFIED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

}