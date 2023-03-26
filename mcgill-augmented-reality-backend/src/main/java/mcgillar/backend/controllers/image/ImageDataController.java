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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import net.sourceforge.tess4j.*;

@RestController
@AllArgsConstructor
@RequestMapping("/image/")
public class ImageDataController {

    private ImageDataService imageDataService;

    @PutMapping("schedule")
    public ResponseEntity<?> uploadScheduleImage(@RequestParam("image") MultipartFile file) throws IOException {
        try {

            // Convert MultipartFile to File
            File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            convertedFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();

            // Initialize Tesseract instance
            Tesseract tesseract = new Tesseract();
            tesseract.setOcrEngineMode(1); // Set OCR engine mode to Tesseract only (LSTM engine)
            tesseract.setPageSegMode(1); // Set page segmentation mode to fully automatic layout analysis
            tesseract.setDatapath("/Users/abhijeetpraveen/mcgill-augmented-reality-app/mcgill-augmented-reality-backend/Tess4J/tessdata"); // Set path to tessdata directory
            tesseract.setLanguage("eng"); // Set language to English

            // Perform OCR on the file
            String result = tesseract.doOCR(convertedFile);
            System.out.println("OCR Text Output: " + result);

            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            imageDataService.uploadImage(file, "schedule_" + name);

        } catch(TesseractException error) {
            System.err.println("Upload Schedule Failed: " + error.getMessage());
            return new ResponseEntity("Upload Schedule Failed: " + error.getMessage(), HttpStatus.FORBIDDEN);
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