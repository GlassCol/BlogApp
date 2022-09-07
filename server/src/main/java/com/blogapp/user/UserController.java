package com.blogapp.user;

<<<<<<< HEAD:server/src/main/java/com/blogapp/user/UserController.java
import com.blogapp.user.domain.User;
import com.blogapp.user.services.IUserService;
=======
import com.blogapp.domains.User;
import com.blogapp.repositories.IUserDao;
import com.blogapp.services.interfaces.IUserService;
>>>>>>> 21a2c00a0318e5e79df0178a813e9ad2509d61e6:server/src/main/java/com/blogapp/controllers/UserController.java
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

    @GetMapping
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @GetMapping("/users")
    User getUserById(@PathVariable Long theId) {
        return userDao.findById(theId).orElseThrow(RuntimeException::new);
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
