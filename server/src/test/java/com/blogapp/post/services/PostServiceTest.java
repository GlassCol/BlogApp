package com.blogapp.post.services;

import com.blogapp.post.domain.Post;
import com.blogapp.post.repositories.IPostDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private IPostDao postDao;

    @InjectMocks
    private PostService postService;


    // TEST GET POST ALL
    @Test
    @DisplayName("Should return all posts")
    void getPostsShouldReturnAllPosts() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);
        when(postDao.findAll()).thenReturn(List.of(post));

        List<Post> posts = postService.getPosts();

        assertEquals(1, posts.size());
        assertEquals("title", posts.get(0).getTitle());
        assertEquals("body", posts.get(0).getBody());
    }

    // TEST GET POST BY USER / POSTER ID
    @Test
    @DisplayName("Should return posts when the user id is valid")
    void getPostsByUserIdWhenUserIdIsValidThenReturnPosts() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);
        when(postDao.findByUserId(anyLong())).thenReturn(List.of(post));

        List<Post> posts = postService.getPostsByUserId(1L);

        assertEquals(1, posts.size());
        assertEquals("title", posts.get(0).getTitle());
        assertEquals("body", posts.get(0).getBody());
    }

    @Test
    @DisplayName("Should throw an exception when the user id is invalid")
    void getPostsByUserIdWhenUserIdIsInvalidThenThrowException() {
        when(postDao.findByUserId(anyLong())).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () -> postService.getPostsByUserId(1L));
    }

    // TEST GET POST BY CATEGORY ID
//    @Test
//    @DisplayName("Should return posts when the category exists")
//    void getPostsByCategoryIdWhenCategoryExistsThenReturnPosts() {
//        Post post =
//                new Post(
//                        1L,
//                        "title",
//                        "body",
//                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
//                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
//                        null,
//                        null,
//                        null);
//        when(postDao.findByCategoryId(anyLong())).thenReturn(List.of(post));
//
//        List<Post> posts = postService.getPostsByCategoryId(1L);
//
//        assertEquals(1, posts.size());
//        assertEquals("title", posts.get(0).getTitle());
//        assertEquals("body", posts.get(0).getBody());
//    }
//
//    @Test
//    @DisplayName("Should throw an exception when the category does not exist")
//    void getPostsByCategoryIdWhenCategoryDoesNotExistThenThrowException() {
//        when(postDao.findByCategoryId(anyLong())).thenReturn(List.of());
//        assertThrows(EntityNotFoundException.class, () -> postService.getPostsByCategoryId(1L));
//    }

    // TEST GET POST BY ID
    @Test
    @DisplayName("Should return null when the post is not found")
    void getPostByIdWhenPostIsNotFoundThenReturnNull() {
        when(postDao.findById(anyLong())).thenReturn(Optional.empty());
        Post post = postService.getPostById(1L);
        assertNull(post);
    }

    @Test
    @DisplayName("Should return the post when the post is found")
    void getPostByIdWhenPostIsFoundThenReturnThePost() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);
        when(postDao.findById(anyLong())).thenReturn(Optional.of(post));

        Post foundPost = postService.getPostById(1L);

        assertEquals(post, foundPost);
    }

    // TEST ADD POST
    @Test
    @DisplayName("Should save the post when the id is not taken")
    void addPostWhenIdIsNotTaken() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);
        when(postDao.existsById(anyLong())).thenReturn(false);
        when(postDao.save(post)).thenReturn(post);

        Post savedPost = postService.addPost(post).get();
        assertEquals(post, savedPost);
    }

    @Test
    @DisplayName("Should throw an exception when the id is already taken")
    void addPostWhenIdIsAlreadyTakenThenThrowException() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);
        when(postDao.existsById(anyLong())).thenReturn(true);
        assertThrows(EntityExistsException.class, () -> postService.addPost(post));
    }

    // TEST UPDATE
    @Test
    @DisplayName("Should update the post when the post exists")
    void updatePostWhenPostExists() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);

        when(postDao.existsById(anyLong())).thenReturn(true);
        when(postDao.save(post)).thenReturn(post);

        assertEquals(postService.updatePost(post), Optional.of(post));
    }

    @Test
    @DisplayName("Should throw an exception when the post does not exist")
    void updatePostWhenPostDoesNotExistThenThrowException() {
        Post post =
                new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(50000, 50000, ZoneOffset.UTC),
                        null,
                        null,
                        null);

        when(postDao.existsById(anyLong())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> postService.updatePost(post));
    }

    // TEST DELETE
    @Test
    @DisplayName("Should return true when the post exists")
    void deletePostByIdWhenPostExistsThenReturnTrue() {
        Post post = new Post(
                        1L,
                        "title",
                        "body",
                        LocalDateTime.now(ZoneOffset.UTC),
                        LocalDateTime.now(ZoneOffset.UTC));
        when(postDao.existsById(anyLong())).thenReturn(true);
        boolean result = postService.deletePostById(1L);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when the post does not exist")
    void deletePostByIdWhenPostDoesNotExistThenReturnFalse() {
        when(postDao.existsById(anyLong())).thenReturn(false);
        boolean result = postService.deletePostById(1L);
        assertFalse(result);
    }

}