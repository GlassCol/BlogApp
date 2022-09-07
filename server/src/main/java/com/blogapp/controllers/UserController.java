package com.blogapp.controllers;

import com.blogapp.domains.User;
import com.blogapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    User getUserById(@PathVariable Long theId) {
        return userService.getUserById(theId);
    }
    @GetMapping("/users")
    User getUserByUsername(@PathVariable String userName){
        return userService.getUserByUsername(userName);
    }
    @GetMapping("/users")
    User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
