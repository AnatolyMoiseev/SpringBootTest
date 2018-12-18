package hello.services.implement;

import hello.domains.Image;
import hello.repositories.ImageRepository;
import hello.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImplement implements ImageService {

    private final ImageRepository imageRepository;

    private Image image = new Image();

    @Value("${files.upload.baseDir}")
    private String uploadPath;

    @Autowired
    public ImageServiceImplement(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void transferFile(MultipartFile file) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        image.setName(uuidFile + originalFileName);
        file.transferTo(new File(uploadPath + "/" + image.getName()));
        imageRepository.save(image);
    }

    @Override
    public String getFileNameImage() {
        return image.getName();
    }
}
