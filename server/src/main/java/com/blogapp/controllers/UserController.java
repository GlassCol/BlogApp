package com.blogapp.controllers;

import com.blogapp.domains.User;
import com.blogapp.repositories.IUserDao;
import com.blogapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Component
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserDao userDao;

    @Autowired
    UserController(IUserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    @GetMapping("/users")
    User getUserById(@PathVariable Long theId) {
        return userDao.findById(theId).orElseThrow(RuntimeException::new);
    }
    @GetMapping("/users")
    User getUserByUsername(@PathVariable String userName){
        return userDao.getUserByUsername(userName);
    }
    @GetMapping("/users")
    User getUserByEmail(@PathVariable String email) {
        return userDao.getUserByEmail(email);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
        User savedUser = userDao.save(user);
        return ResponseEntity.created(new URI("/users/" + savedUser.getId())).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userDao.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setUsername(user.getUsername());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAuthToken(user.getAuthToken());
        currentUser = userDao.save(user);

        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userDao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
