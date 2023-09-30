package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.GiftMapper;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Repository.GiftRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;

    public List<GiftDto> getGiftsByEvent(Long idEvent) {
        List<Gift> gifts = giftRepository.findByEventIdEvent(idEvent);
        return gifts.stream().map(GiftMapper::toDto).collect(Collectors.toList());
    }


//    public Gift addGift(Gift gift){
//        return giftRepository.save(gift);
//    }
//
//    public List<Gift> findAllGifts(){
//        return giftRepository.findAll();
//    }
//
//    public Gift updateGift(Gift gift){
//        return giftRepository.save(gift);
//    }
//
    public Gift findGiftByIdGift(Long idGift){
        return giftRepository.findGiftByIdGift(idGift).orElseThrow(() -> new UserNotFoundException("User n° " + idGift + " was not found"));
    }
//
//    public void deleteGift(Long idGift){
//        giftRepository.deleteGiftByIdGift(idGift);
//    }
}
