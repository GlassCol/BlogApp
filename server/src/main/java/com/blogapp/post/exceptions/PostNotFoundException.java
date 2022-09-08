package com.blogapp.post.exceptions;


public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long theId) {
        super("Could not find post "+ theId);
    }

}
