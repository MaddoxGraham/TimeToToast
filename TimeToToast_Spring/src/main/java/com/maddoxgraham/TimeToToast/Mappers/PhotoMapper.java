package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.PhotoDto;
import com.maddoxgraham.TimeToToast.Models.Photo;

public class PhotoMapper {

    public static PhotoDto toDto(Photo photo) {
        PhotoDto dto = new PhotoDto();
        dto.setIdPhoto(photo.getIdPhoto());
        dto.setName(photo.getName());
        dto.setSource(photo.getSource());
        dto.setCreatedAt(photo.getCreatedAt());
        dto.setEvent(photo.getEvent().getIdEvent());
        dto.setPerson(photo.getPerson().getIdPerson());
        return dto;
    }

    public static Photo toEntity(PhotoDto dto) {
        Photo photo = new Photo();
        photo.setIdPhoto(dto.getIdPhoto());
        photo.setName(dto.getName());
        photo.setSource(dto.getSource());
        photo.setCreatedAt(dto.getCreatedAt());
        return photo;
    }
}
