package com.blogapp.services;

import com.blogapp.domains.User;
import com.blogapp.repositories.IUserDao;
import com.blogapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final static String USER_NOT_FOUND = "user %s not found";


    @Autowired
    UserService(IUserDao userDao) {
        this.userDao = userDao;
    }


}
