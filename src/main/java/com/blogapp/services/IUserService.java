package com.blogapp.services;

import com.blogapp.domains.User;


public interface IUserService {
    User getUserBy(Long theId);
}
