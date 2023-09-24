package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Repository.PhotoRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final EventService eventService;
    private final PersonService personService;

    @Value("${file.upload-dir}")
    private String uploadDir;


    public List<Photo> saveFiles(List<MultipartFile> files, Long idEvent, Long idPerson) throws IOException {
        List<Photo> savedPhotos = new ArrayList<>();

        for (MultipartFile file : files) {
            Photo photo = new Photo();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            photo.setName(fileName);
            photo.setSource(uploadDir + fileName);
            photo.setCreatedAt(LocalDate.now());

            // Utilise les idEvent et idPerson pour obtenir ou créer des objets Event et Person
            photo.setEvent(eventService.findEventByIdEvent(idEvent));
            photo.setPerson(personService.findPersonByIdPerson(idPerson));

            // Sauvegarde du fichier sur disque
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            // Sauvegarde de l'objet Photo dans la base de données, si nécessaire
            // photo = photoRepository.save(photo);  // décommenter si tu souhaites sauvegarder dans la BDD

            savedPhotos.add(photo);
        }

        return savedPhotos;
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
