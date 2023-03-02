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
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }


}