package com.blogapp;

import com.blogapp.category.domain.Category;
import com.blogapp.category.domain.SubCategory;
import com.blogapp.comment.domain.Comment;
import com.blogapp.photo.domain.Photo;
import com.blogapp.user.domain.User;
import com.blogapp.post.domain.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Bean
    @Scope(scopeName = "prototype")
    Category parentCategory() {
        return new Category();
    }

    @Bean
    @Scope(scopeName = "prototype")
    SubCategory subCategory() {
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

//    @Bean
//    CategoryService categoryService() {
//        return new CategoryService();
//    }

}
