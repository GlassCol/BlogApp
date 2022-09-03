package com.blogapp.domains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DomainConfig {

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
