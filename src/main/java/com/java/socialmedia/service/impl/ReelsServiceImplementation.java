package com.java.socialmedia.service.impl;

import com.java.socialmedia.models.Reels;
import com.java.socialmedia.models.User;
import com.java.socialmedia.repository.ReelsRepository;
import com.java.socialmedia.service.ReelsService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReelsServiceImplementation implements ReelsService {
    private ReelsRepository reelsRepository;
    private UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createdReel = new Reels();
        createdReel.setTitle(reel.getTitle());
        createdReel.setVideo(reel.getVideo());
        createdReel.setUser(user);

        return reelsRepository.save(createdReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        return reelsRepository.findByUserId(user.getId());
    }
}
