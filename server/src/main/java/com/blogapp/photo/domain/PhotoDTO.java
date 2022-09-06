package com.blogapp.photo.domain;

import com.blogapp.post.domain.Post;
import com.blogapp.post.domain.PostDto;

import java.time.LocalDateTime;

public class PhotoDTO {

    private Long id;
    private String imageUrl;
    private String title;
    private String imageType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public static Photo mapPostDtoToPhoto(PostDto postDto) {
        Photo photo = postDto.getPhotos().get(0);
        return new Photo(
                photo.getImageUrl(),
                photo.getTitle(),
                photo.getImageType(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

 }
