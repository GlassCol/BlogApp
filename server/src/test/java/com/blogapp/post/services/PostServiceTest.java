package com.blogapp.post.services;

import com.blogapp.photo.services.IPhotoService;
import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDTO;
import com.blogapp.post.repositories.IPostDao;
import com.blogapp.user.domain.User;
import com.blogapp.user.services.IUserService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PostService tests")
class PostServiceTest {

    @Mock
    private IPostDao postDao;

    @Mock
    private IPhotoService photoService;

    @Mock
    private IUserService userService;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("Should return a list of posts when there are posts in the database")
    void getPostsWhenThereArePostsInTheDatabase() {
        List<Post> posts = List.of(new Post(1L, "title", "body", null, null));
        when(postDao.findAll()).thenReturn(posts);

        ResponseEntity<Object> response = postService.getPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when there are no posts in the database")
    void getPostsWhenThereAreNoPostsInTheDatabase() {
        when(postDao.findAll()).thenReturn(List.of());

        ResponseEntity<Object> response = postService.getPosts();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());    }

    @Test
    @DisplayName("Should return a list of posts when the user id is found")
    void getPostsByUserIdWhenUserIdIsFoundThenReturnListOfPosts() {
        Long theUserId = 1L;
        Post post = new Post(1L, "title", "body", null, null);
        List<Post> posts = List.of(post);
        when(postDao.findByUserId(theUserId)).thenReturn(posts);

        ResponseEntity<Object> response = postService.getPostsByUserId(theUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when the user id is not found")
    void getPostsByUserIdWhenUserIdIsNotFoundThenReturnEmptyList() {
        Long userId = 1L;
        when(postDao.findByUserId(userId)).thenReturn(List.of());

        ResponseEntity<Object> response = postService.getPostsByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    @DisplayName("Should return the post when the post exists")
    void getPostByIdWhenPostExists() {
        Long thePostId = 1L;
        Post post = new Post(thePostId, "title", "body", null, null);
        when(postDao.findById(thePostId)).thenReturn(Optional.of(post));

        ResponseEntity<Object> response = postService.getPostById(thePostId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return 404 when the post does not exist")
    void getPostByIdWhenPostDoesNotExistThenReturn404() {
        Long thePostId = 1L;
        when(postDao.findById(thePostId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = postService.getPostById(thePostId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return a list of photos when the post id is valid")
    @Disabled
    void getPhotoByPostIdWhenPostIdIsValidThenReturnListOfPhotos() {
        Long thePostId = 1L;
        Post post = new Post(thePostId, "title", "body", null, null);
        when(postDao.findById(thePostId)).thenReturn(Optional.of(post));

        ResponseEntity<Object> response = postService.getPhotoByPostId(thePostId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when the post id is invalid")
    @Disabled
    void getPhotoByPostIdWhenPostIdIsInvalidThenReturnEmptyList() {
        Long thePostId = 1L;
        when(postDao.findById(thePostId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = postService.getPhotoByPostId(thePostId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should save the post when the user is authorized")
    @Disabled
    void addPostWhenUserIsAuthorized() {
        User user = new User(1L, "username", "firstName", "lastName", "authToken", "email");
        PostDTO postDto = new PostDTO(1L, "title", "body",
                null, null, null, null,
                user.getUsername(), user);

        Post post = PostDTO.mapPostDtoToPost(postDto);
        when(userService.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(postDao.save(post)).thenReturn(post);

        ResponseEntity<Object> response = postService.addPost(postDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should throw an exception when the user is not authorized")
    void addPostWhenUserIsNotAuthorizedThenThrowException() {
        PostDTO postDto = new PostDTO();
        postDto.setUsername("username");
        when(userService.findByUsername(postDto.getUsername())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = postService.addPost(postDto);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should update the post when the user is authorized")
    void updatePostWhenUserIsAuthorized() {
        PostDTO postDto = new PostDTO();
        postDto.setId(1L);
        postDto.setUsername("username");
        User user = new User();
        user.setUsername("username");
        Post existingPost = new Post();
        existingPost.setUser(user);

        when(userService.findByUsername(postDto.getUsername())).thenReturn(Optional.of(user));
        when(postDao.findById(postDto.getId())).thenReturn(Optional.of(existingPost));

        ResponseEntity<Object> response = postService.updatePost(postDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should throw an exception when the user is not authorized")
    void updatePostWhenUserIsNotAuthorizedThenThrowException() {
        PostDTO postDto = new PostDTO();
        postDto.setId(1L);
        postDto.setUsername("username");

        when(userService.findByUsername(postDto.getUsername())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = postService.updatePost(postDto);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should delete the post when the post exists")
    void deletePostByIdWhenPostExists() {
        Long thePostId = 1L;
        when(postDao.existsById(thePostId)).thenReturn(true);
        ResponseEntity<Object> response = postService.deletePostById(thePostId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should throw an exception when the post does not exist")
    void deletePostByIdWhenPostDoesNotExistThenThrowException() {
        Long thePostId = 1L;
        when(postDao.existsById(thePostId)).thenReturn(false);

        ResponseEntity<Object> response = postService.deletePostById(thePostId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}