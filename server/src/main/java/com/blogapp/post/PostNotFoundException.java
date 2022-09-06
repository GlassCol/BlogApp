package com.blogapp.post;


public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long theId) {
        super("Could not find post "+ theId);
    }

}
