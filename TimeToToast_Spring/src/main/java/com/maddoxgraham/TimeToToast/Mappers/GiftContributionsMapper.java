package com.maddoxgraham.TimeToToast.Mappers;


import com.maddoxgraham.TimeToToast.DTOs.GiftContributionDto;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import org.springframework.stereotype.Component;

@Component
public class GiftContributionsMapper {
    public static GiftContributionDto toDto(GiftContribution contribution) {
        GiftContributionDto dto = new GiftContributionDto();
        dto.setIdGiftContribution(contribution.getIdGiftContribution());
        dto.setAmount(contribution.getAmount());
        dto.setIdGift(contribution.getGift().getIdGift());
        dto.setIdPerson(contribution.getPerson().getIdPerson());
        return dto;
    }

    public static GiftContribution toEntity(GiftContributionDto dto) {
         GiftContribution contribution = new GiftContribution();
         contribution.setIdGiftContribution(dto.getIdGiftContribution());
         contribution.setAmount(dto.getAmount());
        return contribution;
    }
}
