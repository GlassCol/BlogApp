package com.blogapp.services.interfaces;

import com.blogapp.domains.User;


public interface IUserService {
    User getUserById(Long theId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);

}
