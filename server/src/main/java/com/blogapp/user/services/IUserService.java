package com.blogapp.user.services;

import com.blogapp.user.domain.User;

import java.util.Optional;


public interface IUserService {
    User getUserBy(Long theId);
    Optional<User> getUserByUsername(String theUsername);
    boolean existsByUsername(String username);
}
