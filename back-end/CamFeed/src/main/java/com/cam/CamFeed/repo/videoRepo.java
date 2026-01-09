package com.cam.CamFeed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cam.CamFeed.model.Video;

public interface videoRepo extends JpaRepository<Video, String> {
    
}
