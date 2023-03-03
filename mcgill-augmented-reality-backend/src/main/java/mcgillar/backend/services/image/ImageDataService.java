package mcgillar.backend.services.image;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mcgillar.backend.model.image.ImageData;
import mcgillar.backend.repositories.image.ImageDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageDataService {

    private ImageDataRepository imageDataRepository;

    public void uploadImage(MultipartFile file, String name) throws IOException {

        imageDataRepository.save(ImageData.builder()
                .name(name)
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

    }

    @Transactional
    public Optional<ImageData> getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return dbImage.map(imageData -> ImageData.builder()
                .name(imageData.getName())
                .type(imageData.getType())
                .imageData(ImageUtil.decompressImage(imageData.getImageData())).build());

    }

    @Transactional
    public Optional<byte[]> getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        if (dbImage.isPresent()) {
            byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
            return Optional.of(image);
        }
        return Optional.empty();
    }

    @Transactional
    public void deleteImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        dbImage.ifPresent(imageData -> imageDataRepository.delete(imageData));
    }

}