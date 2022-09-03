package com.blogapp;

import com.blogapp.category.dto.ParentCategory;
import com.blogapp.category.dto.SubCategory;
import com.blogapp.comment.domain.Comment;
import com.blogapp.category.photo.dto.Photo;
import com.blogapp.user.dto.User;
import com.blogapp.post.domain.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DtoBeanConfig {

    @Bean
    @Scope(scopeName = "prototype")
    ParentCategory parentBlogCategory() {
        return new ParentCategory();
    }

    @Bean
    @Scope(scopeName = "prototype")
    SubCategory childBlogCategory() {
        return new SubCategory();
    }

    @Bean
    @Scope(scopeName = "prototype")
    Post post() {
        return new Post();
    }

    @Bean
    @Scope(scopeName = "prototype")
    Comment comment() {
        return new Comment();
    }

    @Bean
    @Scope(scopeName = "prototype")
    Photo photo() {
        return new Photo();
    }

    @Bean
    @Scope(scopeName = "prototype")
    User user() {
        return new User();
    }

}
