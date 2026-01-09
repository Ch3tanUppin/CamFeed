package com.cam.CamFeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cam.CamFeed.model.Video;
import com.cam.CamFeed.service.VideoService;



@RestController
@RequestMapping("/api/videos")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/health")
    public String health() {
        return "ok";
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) throws Exception {
        String id = videoService.saveVideo(file);
        return ResponseEntity.ok().body("Video uploaded with ID: " + id);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> stream(@PathVariable String id){
        Video video = videoService.getVideo(id);
        return ResponseEntity.ok()
        .contentType(MediaType.valueOf("video/webm"))
        .body(video);
    }
}
