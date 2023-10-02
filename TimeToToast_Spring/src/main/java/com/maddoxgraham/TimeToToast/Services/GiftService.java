package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.GiftMapper;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import com.maddoxgraham.TimeToToast.Repository.GiftContributionRepository;
import com.maddoxgraham.TimeToToast.Repository.GiftRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;
    private final GiftContributionRepository giftContributionRepository;
    private final GiftMapper giftMapper;
    private final EventService eventService;


    public List<GiftDto> getGiftsByEvent(Long idEvent) {
        List<Gift> gifts = giftRepository.findByEventIdEvent(idEvent);
        return gifts.stream().map(GiftMapper::toDto).collect(Collectors.toList());
    }

    public GiftDto updateisPaid(GiftDto dto) {
        Optional<Gift> optionalGift = giftRepository.findGiftByIdGift(dto.getIdGift());
        if (optionalGift.isPresent()) {
            Gift gift = optionalGift.get();
            gift.setPaid(true);
            Gift updatedPaid = giftRepository.save(gift);
            return GiftMapper.toDto(updatedPaid);
        }
        return null;
    }
      
     public GiftDto addGift(GiftDto dto){
            Gift gift = GiftMapper.toEntity(dto);
            gift.setPaid(false);
            Event event = eventService.findEventByIdEvent(dto.getEvent());
            gift.setEvent(event);
            Gift savedGift = giftRepository.save(gift);
            return GiftMapper.toDto(savedGift);
     }

    public Gift findGiftByIdGift(Long idGift){
        return giftRepository.findGiftByIdGift(idGift).orElseThrow(() -> new UserNotFoundException("User n° " + idGift + " was not found"));
    }

    public void deleteGiftsOfEvent(Long idEvent) {
        List<Gift> gifts =  giftRepository.findByEventIdEvent(idEvent);
        for(Gift gift: gifts) {
            List<GiftContribution> giftContributionList = giftContributionRepository.findByGiftIdGift(gift.getIdGift());
            for(GiftContribution giftContribution: giftContributionList){
                giftContributionRepository.deleteById(giftContribution.getIdGiftContribution());
            }
            giftRepository.deleteById(gift.getIdGift());
        }

    }

}
