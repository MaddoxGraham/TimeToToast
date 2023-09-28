package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.GiftContributionDto;
import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.Mappers.GiftContributionsMapper;
import com.maddoxgraham.TimeToToast.Mappers.GiftMapper;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import com.maddoxgraham.TimeToToast.Repository.GiftContributionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GiftContributionService {
    private final GiftContributionRepository giftContributionRepository;

   public List<GiftContributionDto> findByIdGift(Long idGift) {
       List<GiftContribution> contributions = giftContributionRepository.findByGiftIdGift(idGift);
       return contributions.stream().map(GiftContributionsMapper::toDto).collect(Collectors.toList());
    }

}
