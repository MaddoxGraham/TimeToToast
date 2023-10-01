package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.PhotoDto;
import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Services.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/photo")
@AllArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/upload/{idEvent}/{idPerson}")
    public ResponseEntity<?> uploadPhotos(@PathVariable("idEvent") Long idEvent, @PathVariable("idPerson") Long idPerson, @RequestParam("files") List<MultipartFile> files) {
        try {
            List<Photo> newPhotos = photoService.saveFiles(files, idEvent, idPerson);
            return new ResponseEntity<>(newPhotos, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findEventPhoto/{idEvent}")
    public ResponseEntity<List<Map<String, Object>>> getPhotoByEvent(@PathVariable("idEvent") Long idEvent) throws IOException {
        List<PhotoDto> photos = photoService.findPhotoByIdEvent(idEvent);
        List<Map<String, Object>> jsonMapList = new ArrayList<>();

        for (PhotoDto photo : photos){
            String imagesPath = photo.getSource();

            File file = new File(imagesPath);
            if (!file.exists() || !file.isFile()) {
                throw new FileNotFoundException("Le fichier " + imagesPath + " n'existe pas");
            }

            String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));

            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("content", encodeImage);
            jsonMap.put("personId", photo.getPerson());
            jsonMap.put("photoId", photo.getIdPhoto());

            jsonMapList.add(jsonMap);
        }

        return new ResponseEntity<>(jsonMapList, HttpStatus.OK);
    }

    @GetMapping("/uploads/{imageName}")
    public ResponseEntity<String> getImageUrl(@PathVariable String imageName) {
        Path path = Paths.get("uploads/" + imageName);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource.getURI().toString());
            }
        } catch (Exception e) {
            // Handle exception
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{idPhoto}")
    public ResponseEntity<Map<String, String>> deletePhoto(@PathVariable Long idPhoto){
        photoService.deletePhoto(idPhoto);
        Map<String, String> message = new HashMap<>();
        message.put("message", "photo supprimée");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
