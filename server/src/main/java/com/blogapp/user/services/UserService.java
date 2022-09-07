package com.blogapp.user.services;

import com.blogapp.user.domain.User;
import com.blogapp.user.repositories.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final static String USER_NOT_FOUND = "user %s not found";

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

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

}
