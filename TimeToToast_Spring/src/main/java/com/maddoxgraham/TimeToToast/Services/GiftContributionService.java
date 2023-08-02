package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import com.maddoxgraham.TimeToToast.Repository.GiftContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftContributionService {
    private final GiftContributionRepository giftContributionRepository;

    @Autowired
    public GiftContributionService(GiftContributionRepository giftContributionRepository) {
        this.giftContributionRepository = giftContributionRepository;
    }

    public GiftContribution addGiftContribution(GiftContribution giftContribution){
        return giftContributionRepository.save(giftContribution);
    }

    public List<GiftContribution> findAllGiftContributions(){
        return giftContributionRepository.findAll();
    }

    public GiftContribution updateGiftContribution(GiftContribution giftContribution){
        return giftContributionRepository.save(giftContribution);
    }

    public GiftContribution findGiftContributionByIdGiftContribution(Long idGiftContribution){
        return giftContributionRepository.findGiftContributionByIdGiftContribution(idGiftContribution).orElseThrow(() -> new UserNotFoundException("User n° " + idGiftContribution + " was not found"));
    }

    public void deleteGiftContribution(Long idGiftContribution){
        giftContributionRepository.deleteGiftContributionByIdGiftContribution(idGiftContribution);
    }
}
