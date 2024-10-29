package com.java.socialmedia.controller;

import com.java.socialmedia.models.Reels;
import com.java.socialmedia.models.User;
import com.java.socialmedia.service.ReelsService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReelsController {
    private ReelsService reelsService;
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReel(@RequestBody Reels reel,
                            @RequestHeader("Authorization") String jwt) {

        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReel = reelsService.createReel(reel, reqUser);

        return createdReel;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();

        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        List<Reels> reels = reelsService.findUserReels(userId);

        return reels;
    }
}
