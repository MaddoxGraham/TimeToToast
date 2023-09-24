package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

        public Photo saveFile(MultipartFile file, Long idEvent, Long idPerson) {
        Photo photo = new Photo();
        photo.setName(file.getName());

            // Logique pour enregistrer le fichier
            // Créer et retourner un nouvel objet Photo
        }


    public List<Photo> findAllPhotos(){
        return photoRepository.findAll();
    }

    public Photo updatePhoto(Photo photo){
        return photoRepository.save(photo);
    }

    public Photo findPhotoByIdPhoto(Long idPhoto){
        return photoRepository.findPhotoByIdPhoto(idPhoto).orElseThrow(() -> new UserNotFoundException("User n° " + idPhoto + " was not found"));
    }

    public void deletePhoto(Long idPhoto){
        photoRepository.deletePhotoByIdPhoto(idPhoto);
    }
}
