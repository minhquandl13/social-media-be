package com.java.social_media.controller;

import com.java.social_media.models.User;
import com.java.social_media.repository.UserRepository;
import com.java.social_media.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@AllArgsConstructor
public class UserController {
    private UserRepository repository;
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> getUser() {
        List<User> users = repository.findAll();

        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        return userService.findUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        return savedUser;
    }

    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User requestedUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, requestedUser.getId());

        return updatedUser;
    }

    @PutMapping("/api/users/follow/{followerId}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable("followerId") Integer followerId) throws Exception {
        User requestedUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(requestedUser.getId(), followerId);

        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);

        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);

        user.setPassword(null);

        return user;
    }
}
