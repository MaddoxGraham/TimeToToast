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

    public List<Photo> saveFiles(List<MultipartFile> files, Long idEvent, Long idPerson) throws IOException, FileTooLargeException, InvalidFileTypeException {
        List<Photo> savedPhotos = new ArrayList<>();
        final long MAX_SIZE = 5 * 1024 * 1024; // 5MB, ajuste selon tes besoins

        for (MultipartFile file : files) {
            // Validation de la taille du fichier
            if (file.getSize() > MAX_SIZE) {
                throw new FileTooLargeException("File is too large");
            }

            // Validation du type du fichier
            String contentType = file.getContentType();
            if (!contentType.equals("image/jpeg") && !contentType.equals("image/png") && !contentType.endsWith(".jpg")) {
                throw new InvalidFileTypeException("Invalid file type");
            }

            Photo photo = new Photo();
            // Récupération ou création des objets Event et Person
            photo.setEvent(eventService.findEventByIdEvent(idEvent));
            photo.setPerson(personService.findPersonByIdPerson(idPerson));

            String fileName = + idEvent + idPerson + System.currentTimeMillis() + "_" + file.getOriginalFilename() ;
            photo.setName(fileName);
            photo.setSource(uploadDir + fileName);
            photo.setCreatedAt(LocalDate.now());

            // Sauvegarde du fichier sur disque
            Path filePath = Paths.get(uploadDir + fileName);
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
