package mcgillar.backend.repositories.image;

import mcgillar.backend.model.image.ImageData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageDataRepository extends CrudRepository<ImageData, Integer> {
    Optional<ImageData> findByName(String name);
}
