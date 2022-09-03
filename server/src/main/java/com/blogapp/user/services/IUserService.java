package com.blogapp.user.services;

import com.blogapp.user.domain.User;


public interface IUserService {
    User getUserBy(Long theId);
}
