package com.college.lostandfoundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {

    // Directory relative to project root where images are saved
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        try {
            // Create the directory if it doesn't exist
            File uploadDirFile = new File(UPLOAD_DIR);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Generate unique file name
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + extension;

            // Construct absolute file path
            Path absolutePath = Paths.get(uploadDirFile.getAbsolutePath(), newFilename);

            // Log the path where the file will be saved
            System.out.println("Attempting to save image to: " + absolutePath.toString());

            // Save file
            Files.copy(file.getInputStream(), absolutePath);

            // Return public-facing URL
            return ResponseEntity.ok("/images/" + newFilename);

        } catch (IOException e) {
            System.err.println("Failed to upload image due to: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to upload image: " + e.getMessage());
        }
    }
}
