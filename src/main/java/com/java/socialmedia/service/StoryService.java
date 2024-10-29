package com.java.socialmedia.service;

import com.java.socialmedia.models.Story;
import com.java.socialmedia.models.User;

import java.util.List;

public interface StoryService {
    Story createStory(Story story, User user);

    List<Story> findStoryByUserId(Integer userId) throws Exception;
}
