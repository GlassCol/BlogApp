package com.blogapp.user.services;


import com.blogapp.user.domain.User;

import java.util.List;
import java.util.Optional;


public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long theId);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

    boolean existsByUsername(String username);
}
