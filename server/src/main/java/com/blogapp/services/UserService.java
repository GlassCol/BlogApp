package com.blogapp.services;

import com.blogapp.domains.User;
import com.blogapp.repositories.IUserDao;
import com.blogapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;


    @Autowired
    UserService(IUserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Long theId) {
        return Optional.of(userDao.findById(theId).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
