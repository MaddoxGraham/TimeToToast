package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.PhotoDto;
import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Services.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<String>> getPhotoByEvent(@PathVariable("idEvent") Long idEvent) {
        List<PhotoDto> photos = photoService.findPhotoByIdEvent(idEvent);
        List<String> pathPhotos = new ArrayList<>();
        for (PhotoDto photo : photos){
            Path path = Paths.get(photo.getSource());
            Resource resource;
            try {
                resource = new UrlResource(path.toUri());
                if (resource.exists() || resource.isReadable()) {
                    //return ResponseEntity.ok().body(resource.getURI().toString());
                    pathPhotos.add(resource.getURI().toString());
                }
            } catch (Exception e) {
                // Handle exception
            }
            //return ResponseEntity.notFound().build();
        }

       return new ResponseEntity<>(pathPhotos, HttpStatus.OK);
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

}
