package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.GiftContributionDto;
import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import com.maddoxgraham.TimeToToast.Services.GiftContributionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftContribution")
@AllArgsConstructor
public class GiftContributionController {

    private final GiftContributionService giftContributionService;

    @GetMapping("/getContribution/{idGift}")
    public ResponseEntity<List<GiftContributionDto>> getContribution(@PathVariable("idGift") Long idGift) {
        List<GiftContributionDto> giftContributionDtos = giftContributionService.findByIdGift(idGift);
        return new ResponseEntity<>(giftContributionDtos, HttpStatus.OK);
    }

    @PostMapping("addContribution")
    public ResponseEntity<GiftContributionDto> addContribution(@RequestBody  GiftContributionDto newContribution) {
    GiftContributionDto createdContribution = giftContributionService.addContribution(newContribution);
    return new ResponseEntity<>(createdContribution, HttpStatus.OK);
}
}
