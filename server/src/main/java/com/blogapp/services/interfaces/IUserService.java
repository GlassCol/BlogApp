package com.blogapp.services.interfaces;

import com.blogapp.domains.User;

import java.util.List;
import java.util.Optional;


public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long theId);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void deleteById(Long id);
}
