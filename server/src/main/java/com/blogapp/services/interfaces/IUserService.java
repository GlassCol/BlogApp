package com.blogapp.services.interfaces;

import com.blogapp.domains.User;


public interface IUserService {
    User getUserBy(Long theId);
}