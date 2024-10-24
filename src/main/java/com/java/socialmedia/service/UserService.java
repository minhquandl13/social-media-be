package com.java.socialmedia.service;

import com.java.socialmedia.models.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);

    User findUserById(Integer userId) throws Exception;

    User findUserByEmail(String email);

    User followUser(Integer userId, Integer followerId) throws Exception;

    User updateUser(User user, Integer userId) throws Exception;

    List<User> searchUsers(String query);

    User findUserByJwt(String jwt);
}
