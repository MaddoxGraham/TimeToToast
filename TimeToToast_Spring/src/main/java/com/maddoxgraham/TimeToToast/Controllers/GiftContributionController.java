package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import com.maddoxgraham.TimeToToast.Services.GiftContributionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftContribution")
public class GiftContributionController {

    private final GiftContributionService giftContributionService;

    public GiftContributionController(GiftContributionService giftContributionService) {
        this.giftContributionService = giftContributionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GiftContribution>> getAllGiftContribution() {
        List<GiftContribution> giftContributions = giftContributionService.findAllGiftContributions();
        return new ResponseEntity<>(giftContributions, HttpStatus.OK);
    }

    @GetMapping("/find/{idGiftContribution}")
    public ResponseEntity<GiftContribution> getGiftContributionById(@PathVariable("idGiftContribution") Long idGiftContribution) {
        GiftContribution giftContribution = giftContributionService.findGiftContributionByIdGiftContribution(idGiftContribution);
        return new ResponseEntity<>(giftContribution, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GiftContribution> addGiftContribution(@RequestBody GiftContribution giftContribution){
        GiftContribution newGiftContribution = giftContributionService.addGiftContribution(giftContribution);
        return new ResponseEntity<>(newGiftContribution, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<GiftContribution> updateGiftContribution(@RequestBody GiftContribution giftContribution){
        GiftContribution updateGiftContribution = giftContributionService.updateGiftContribution(giftContribution);
        return new ResponseEntity<>(updateGiftContribution, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idGiftContribution}")
    public ResponseEntity<?> deleteGiftContribution(@PathVariable("idGiftContribution") Long idGiftContribution){
        giftContributionService.deleteGiftContribution(idGiftContribution);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
