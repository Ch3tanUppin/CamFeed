package com.cam.CamFeed.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;  
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cam.CamFeed.model.Video;
import com.cam.CamFeed.repo.videoRepo;

@Service
public class VideoService {

    private final videoRepo videorepository;
    private static final String UPLOAD_DIR = "uploads/videos/";

    public VideoService(videoRepo videoRepo) {
        this.videorepository = videoRepo;
    }

    public String saveVideo(MultipartFile file) throws Exception {
        Files.createDirectories(Path.of(UPLOAD_DIR));

        String id = UUID.randomUUID().toString();
        Path filePath = Path.of(UPLOAD_DIR + id + ".webm");

        Files.copy(file.getInputStream(), filePath);

        Video video = new Video();
        video.setId(id);
        video.setFilePath(filePath.toString());
        video.setCreatedAt(LocalDateTime.now());

        videorepository.save(video);

        return id;
    }

    public Video getVideo(String id) {
        return videorepository.findById(id).orElseThrow();
    }
    public void deleteVideo(String id){
        Video video = videorepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Video Not Found"));

        // delete file from storage
        File file = new File(video.getFilePath());
        if (file.exists()) {
            file.delete();
        }
        videorepository.deleteById(id);
    }
}
