package com.blogapp.domains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DomainConfig {

    @Bean
    @Scope(scopeName = "prototype")
    ParentBlogCategory parentBlogCategory() {
        return new ParentBlogCategory();
    }

    @Bean
    @Scope(scopeName = "prototype")
    ChildBlogCategory childBlogCategory() {
        return new ChildBlogCategory();
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
    User user() {
        return new User();
    }

}
