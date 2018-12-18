package hello.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void transferFile(MultipartFile file) throws IOException;

    String getFileNameImage();
}
