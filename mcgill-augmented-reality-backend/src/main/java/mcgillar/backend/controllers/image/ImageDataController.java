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
import org.bytedeco.leptonica.global.lept;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.tesseract.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/image/")
public class ImageDataController {

    private ImageDataService imageDataService;

    @PutMapping("schedule")
    public ResponseEntity<?> uploadScheduleImage(@RequestParam("image") MultipartFile file) throws IOException {
        try (TessBaseAPI api = new TessBaseAPI()){
            // Convert MultipartFile to byte array
            byte[] fileBytes = file.getBytes();

            // Create PIX object from byte array
            PIX image = lept.pixReadMem(fileBytes, fileBytes.length);

            // OCR using Tesseract
            BytePointer outText;

            if (api.Init(null, "eng") != 0) {
                System.err.println("Could not initialize tesseract.");
                System.exit(1);
            }

            api.SetImage(image);
            outText = api.GetUTF8Text();

            // Print OCR result to console
            System.out.println("OCR output:\n" + outText.getString());

            api.End();
            outText.deallocate();
            lept.pixDestroy(image);

            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            imageDataService.uploadImage(file, "schedule_" + name);
        } catch(IOException error) {
            System.err.println(error.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("profile")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("image") MultipartFile file) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        imageDataService.uploadImage(file, "profile_" + name);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("info/schedule")
    public ResponseEntity<?>  getScheduleImageInfoByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<ImageData> image = imageDataService.getInfoByImageByName("schedule_" + name);
        return image.map(imageData -> ResponseEntity.status(HttpStatus.OK)
                .body(imageData)).orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));

    }

    @GetMapping("info/profile")
    public ResponseEntity<?>  getProfileImageInfoByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<ImageData> image = imageDataService.getInfoByImageByName("profile_" + name);
        return image.map(imageData -> ResponseEntity.status(HttpStatus.OK)
                .body(imageData)).orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("schedule")
    public ResponseEntity<?>  getScheduleImageByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<byte[]> image = imageDataService.getImage("schedule_" + name);

        return image.map(bytes -> ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(bytes)).orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("profile")
    public ResponseEntity<?>  getProfileImageByName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<byte[]> image = imageDataService.getImage("profile_" + name);

        return image.map(bytes -> ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(bytes)).orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("schedule")
    public ResponseEntity<?> deleteScheduleImageByName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        imageDataService.deleteImage("schedule_" + name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("profile")
    public ResponseEntity<?> deleteProfileImageByName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        imageDataService.deleteImage("profile_" + name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}