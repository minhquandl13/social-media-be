package com.java.socialmedia.service.impl;

import com.java.socialmedia.config.JwtProvider;
import com.java.socialmedia.models.User;
import com.java.socialmedia.repository.UserRepository;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }

        throw new Exception("User not exist with id " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public User followUser(Integer userId, Integer followerId) throws Exception {
        User userToFollow = findUserById(userId);
        User follower = findUserById(followerId);

        // Add followerId to the list of followers of the user being followed
        userToFollow.getFollowers().add(follower.getId());

        // Add userId to the list of followings of the follower
        follower.getFollowings().add(userToFollow.getId());

        // Save both user and follower updates
        userRepository.save(userToFollow);
        userRepository.save(follower);

        return userToFollow;
    }

    @Override
    public User updateUser(User newUserDetails, Integer id) throws Exception {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User does not exist with id " + id));

        // Update fields if they are not null
        if (newUserDetails.getFirstName() != null) {
            existingUser.setFirstName(newUserDetails.getFirstName());
        }
        if (newUserDetails.getLastName() != null) {
            existingUser.setLastName(newUserDetails.getLastName());
        }
        if (newUserDetails.getEmail() != null) {
            existingUser.setEmail(newUserDetails.getEmail());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public List<User> searchUsers(String query) {
        return userRepository.searchByQuery(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        return user;
    }
}
