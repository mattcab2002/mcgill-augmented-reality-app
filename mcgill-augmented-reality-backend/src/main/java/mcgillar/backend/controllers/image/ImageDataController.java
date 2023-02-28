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

    @PostMapping("schedule")
    public ResponseEntity<?> uploadScheduleImage(@RequestParam("image") MultipartFile file) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        imageDataService.uploadImage(file, "schedule_" + name);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("profile")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("image") MultipartFile file) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        imageDataService.uploadImage(file, "profile_" + name);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("info/schedule")
    public ResponseEntity<?>  getScheduleImageInfoByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ImageData image = imageDataService.getInfoByImageByName("schedule_" + name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("info/profile")
    public ResponseEntity<?>  getProfileImageInfoByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ImageData image = imageDataService.getInfoByImageByName("profile_" + name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("schedule")
    public ResponseEntity<?>  getScheduleImageByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        byte[] image = imageDataService.getImage("schedule_" + name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping("profile")
    public ResponseEntity<?>  getProfileImageByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        byte[] image = imageDataService.getImage("profile_" + name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}