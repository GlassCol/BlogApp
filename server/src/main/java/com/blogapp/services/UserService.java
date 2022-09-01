package com.blogapp.services;

import com.blogapp.domains.User;
import com.blogapp.repositories.IUserDao;
import com.blogapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;

    @Autowired
    UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserBy(Long theId) {
        return userDao.findById(theId).orElse(null);
    }
}
