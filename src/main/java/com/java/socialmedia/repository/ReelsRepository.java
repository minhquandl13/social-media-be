package com.java.socialmedia.repository;

import com.java.socialmedia.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {
    List<Reels> findByUserId(Integer userId);
}
