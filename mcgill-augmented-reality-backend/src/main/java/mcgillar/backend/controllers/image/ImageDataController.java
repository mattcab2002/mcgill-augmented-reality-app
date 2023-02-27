package mcgillar.backend.controllers.image;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.image.ImageData;
import mcgillar.backend.services.image.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/image/")
public class ImageDataController {

    private ImageDataService imageDataService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        imageDataService.uploadImage(file, name);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("info/")
    public ResponseEntity<?>  getImageInfoByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping()
    public ResponseEntity<?>  getImageByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        byte[] image = imageDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}