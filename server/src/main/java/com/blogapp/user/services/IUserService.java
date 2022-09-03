package com.blogapp.user.services;

import com.blogapp.user.dto.User;


public interface IUserService {
    User getUserBy(Long theId);
}
