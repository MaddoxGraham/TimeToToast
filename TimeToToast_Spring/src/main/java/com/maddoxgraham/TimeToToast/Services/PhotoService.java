package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.PhotoDto;
import com.maddoxgraham.TimeToToast.Exception.FileTooLargeException;
import com.maddoxgraham.TimeToToast.Exception.InvalidFileTypeException;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.PhotoMapper;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Photo;
import com.maddoxgraham.TimeToToast.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PhotoService {

    private final PhotoRepository photoRepository;
    private final EventService eventService;
    private final PersonService personService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public PhotoService(PhotoRepository photoRepository, EventService eventService, PersonService personService) {
        this.photoRepository = photoRepository;
        this.eventService = eventService;
        this.personService = personService;
    }

    public List<Photo> saveFiles(List<MultipartFile> files, Long idEvent, Long idPerson) throws IOException {
        List<Photo> savedPhotos = new ArrayList<>();

        for (MultipartFile file : files) {
            Photo photo = new Photo();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            photo.setName(fileName);

            // Assurez-vous que le chemin complet est correct
            Path filePath = Paths.get(uploadDir + fileName);

            // Composez correctement le chemin de la source
            photo.setSource(filePath.toString());
            photo.setCreatedAt(LocalDate.now());

            photo.setEvent(eventService.findEventByIdEvent(idEvent));
            photo.setPerson(personService.findPersonByIdPerson(idPerson));


            Files.write(filePath, file.getBytes());

            // Sauvegarde de l'objet Photo dans la base de données
            photo = photoRepository.save(photo);

            savedPhotos.add(photo);
        }

        return savedPhotos;
    }



    public List<PhotoDto> findPhotoByIdEvent(Long idEvent) {
        Event event = eventService.findEventByIdEvent(idEvent);
        List<Photo> photos = photoRepository.findPhotoByEvent(event).orElse(Collections.emptyList());
        return photos.stream()
                .map(PhotoMapper::toDto)
                .collect(Collectors.toList());
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
