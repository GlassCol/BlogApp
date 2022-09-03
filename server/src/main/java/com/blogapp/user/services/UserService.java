package com.blogapp.user.services;

import com.blogapp.user.domain.User;
import com.blogapp.user.repositories.IUserDao;
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
