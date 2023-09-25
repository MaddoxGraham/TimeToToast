package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    void deletePhotoByIdPhoto(Long idPhoto);
    Optional<Photo> findPhotoByIdPhoto(Long idPhoto);

    Optional<List<Photo>> findPhotoByEvent(Event event);
}

