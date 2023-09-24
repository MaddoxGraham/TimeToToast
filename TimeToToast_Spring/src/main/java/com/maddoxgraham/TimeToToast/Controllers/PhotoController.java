package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Services.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/photo")
@AllArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllPhoto() {
        List<Photo> photos = photoService.findAllPhotos();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @PostMapping("/upload/{idEvent}/{idPerson}")
    public ResponseEntity<?> uploadPhotos(@PathVariable("idEvent") Long idEvent, @PathVariable("idPerson") Long idPerson, @RequestParam("files") List<MultipartFile> files) {
        try {
            List<Photo> newPhotos = photoService.saveFiles(files, idEvent, idPerson);
            return new ResponseEntity<>(newPhotos, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/find/{idPhoto}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable("idPhoto") Long idPhoto) {
        Photo photo = photoService.findPhotoByIdPhoto(idPhoto);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Photo> addPhoto(@RequestBody Photo photo){
//        Photo newPhoto = photoService.addPhoto(photo);
//        return new ResponseEntity<>(newPhoto, HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo photo){
        Photo updatePhoto = photoService.updatePhoto(photo);
        return new ResponseEntity<>(updatePhoto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idPhoto}")
    public ResponseEntity<?> deletePhoto(@PathVariable("idPhoto") Long idPhoto){
        photoService.deletePhoto(idPhoto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
