package com.blogapp.user;

import com.blogapp.user.domain.User;
import com.blogapp.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    private final static String USER_NOT_FOUND = "user %s not found";
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    // todo - remove the users prefix, since it was defined in @RequestMapping, otherwise route would be users/users/{theId}
    // todo - fix url to be specific, i.e. /id/{email}, otherwise ambiguous mapping error since there are multiple get routes
    @GetMapping("/users/{theId}")
    User getUserById(@PathVariable Long theId) {
        return userService.findById(theId).orElse(null);
    }

    @GetMapping("/username/{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        //Use multimap to display feedback to users

        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // todo - fix url to be specific, i.e. /email/{email}, otherwise ambiguous mapping error since there are multiple get routes
    @GetMapping("/{email}")
    User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        User savedUser = userService.save(user);
//        return ResponseEntity.created(new URI("/users/" + savedUser.getId())).body(savedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userService.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setUsername(user.getUsername());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAuthToken(user.getAuthToken());
        currentUser = userService.save(user);

        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
