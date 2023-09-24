package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Services.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/upload")
        public ResponseEntity<List<Photo>> uploadPhotos(){
        //modifier findallphoto en uploadPHOTOS !!!!!!!!!!!!!!!!!!!
        List<Photo> photos = photoService.findAllPhotos();
        return new ResponseEntity<>(photos, HttpStatus.OK);
        }

    @GetMapping("/find/{idPhoto}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable("idPhoto") Long idPhoto) {
        Photo photo = photoService.findPhotoByIdPhoto(idPhoto);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Photo> addPhoto(@RequestBody Photo photo){
        Photo newPhoto = photoService.addPhoto(photo);
        return new ResponseEntity<>(newPhoto, HttpStatus.CREATED);
    }

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
