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


    @Override
    public User getUserById(Long theId) {
        return userDao.findById(theId).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
        //code for if there is an error
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
        //code for if there is an error
    }

}
