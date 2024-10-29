package com.java.socialmedia.repository;

import com.java.socialmedia.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    List<Story> findByUserId(Integer userId);
}
