package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService {
    private final GiftRepository giftRepository;

    @Autowired
    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Gift addGift(Gift gift){
        return giftRepository.save(gift);
    }

    public List<Gift> findAllGifts(){
        return giftRepository.findAll();
    }

    public Gift updateGift(Gift gift){
        return giftRepository.save(gift);
    }

    public Gift findGiftByIdGift(Long idGift){
        return giftRepository.findGiftByIdGift(idGift).orElseThrow(() -> new UserNotFoundException("User n° " + idGift + " was not found"));
    }

    public void deleteGift(Long idGift){
        giftRepository.deleteGiftByIdGift(idGift);
    }
}
