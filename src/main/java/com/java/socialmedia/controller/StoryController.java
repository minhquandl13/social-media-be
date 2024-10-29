package com.java.socialmedia.controller;

import com.java.socialmedia.models.Story;
import com.java.socialmedia.models.User;
import com.java.socialmedia.service.StoryService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoryController {
    private StoryService storyService;
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story,
                             @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        Story newStory = storyService.createStory(story, reqUser);

        return newStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Integer userId) throws Exception {
        List<Story> stories = storyService.findStoryByUserId(userId);

        return stories;
    }
}
