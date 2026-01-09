package com.cam.CamFeed.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Video {
    

    @Id
    private String id;
    
    private String filePath;

    private LocalDateTime createdAt;
}
