package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    void deletePhotoByIdPhoto(Long idPhoto);
    Optional<Photo> findPhotoByIdPhoto(Long idPhoto);
}

