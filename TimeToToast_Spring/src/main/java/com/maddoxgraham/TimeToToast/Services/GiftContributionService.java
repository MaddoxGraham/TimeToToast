package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.GiftContributionDto;
import com.maddoxgraham.TimeToToast.Mappers.GiftContributionsMapper;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import com.maddoxgraham.TimeToToast.Repository.GiftContributionRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GiftContributionService {
    private final GiftContributionRepository giftContributionRepository;
    private final GiftContributionsMapper giftContributionMapper;
    private final GiftService giftService;
    private final PersonService personService;

   public List<GiftContributionDto> findByIdGift(Long idGift) {
       List<GiftContribution> contributions = giftContributionRepository.findByGiftIdGift(idGift);
       return contributions.stream().map(GiftContributionsMapper::toDto).collect(Collectors.toList());
    }

    public GiftContributionDto addContribution(GiftContributionDto newContribution){

        GiftContribution contribution = giftContributionMapper.toEntity(newContribution);
       contribution.setGift(giftService.findGiftByIdGift(newContribution.getIdGift()));
       contribution.setPerson(personService.findPersonByIdPerson(newContribution.getIdPerson()));
        GiftContribution savedContribution = giftContributionRepository.save(contribution);

        return giftContributionMapper.toDto(savedContribution);
    }

    public void deleteGiftContributionFromIdGift(Long idGift) {
       List<GiftContribution> giftContributionList = giftContributionRepository.findByGiftIdGift(idGift);
       for(GiftContribution giftContribution: giftContributionList){
           giftContributionRepository.delete(giftContribution);
       }
    }
}
