package hello.controllers;

import hello.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/image")
public class ImageController {

    @Value("${files.upload.baseDir}")
    private String uploadPath;

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/upload")
    public String upload() {
        return "uploadImage";
    }

    @PostMapping("/uploadImage")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                imageService.transferFile(file);
                return ResponseEntity.ok().body(uploadPath + "/" + imageService.getFileNameImage());
            } catch (IOException e) {
                return ResponseEntity.badRequest().body(e.getMessage());

            }
        } else {
            return ResponseEntity.badRequest().body("Empty file");

        }
    }
}
