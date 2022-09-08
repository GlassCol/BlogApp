package com.blogapp.post.domain;

import com.blogapp.photo.domain.Photo;
import com.blogapp.comment.domain.Comment;
import com.blogapp.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    public PostDTO() {}

    public PostDTO(Long id, String title, String body, LocalDateTime updatedAt, LocalDateTime createdAt, List<Comment> comments, List<Photo> photos, String username, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.comments = comments;
        this.photos = photos;
        this.username = username;
        this.user = user;
    }

    private Long id;

    private String title;

    private String body;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private List<Comment> comments;

    private List<Photo> photos;

    private String username;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static Post mapPostDtoToPost(PostDTO postDto) {
        return new Post(
                postDto.getId(),
                postDto.getTitle(),
                postDto.getBody(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                postDto.getUser()
        );
    }

}
