package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.DTOs.PhotoDto;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Models.Photo;
import org.springframework.stereotype.Component;

@Component
public class GiftMapper {
    public static GiftDto toDto(Gift gift) {
        GiftDto dto = new GiftDto();
        dto.setIdGift(gift.getIdGift());
        dto.setName(gift.getName());
        dto.setUrl(gift.getUrl());
        dto.setPhoto(gift.getPhoto());
        dto.setWanted(gift.getWanted());
        dto.setPrice(gift.getPrice());
        dto.setCategorie(gift.getCategorie());
        dto.setPaid(gift.isPaid());
        dto.setEvent(gift.getEvent().getIdEvent());

        return dto;
    }

    public static Gift toEntity(GiftDto dto) {
        Gift gift = new Gift();
        gift.setIdGift(dto.getIdGift());
        gift.setName(dto.getName());
        gift.setUrl(dto.getUrl());
        gift.setPhoto(dto.getPhoto());
        gift.setWanted(dto.getWanted());
        gift.setPrice(dto.getPrice());
        gift.setCategorie(dto.getCategorie());
        gift.setPaid(dto.isPaid());
        return gift;
    }
}
