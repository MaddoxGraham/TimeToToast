package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Services.GiftContributionService;
import com.maddoxgraham.TimeToToast.Services.GiftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gift")
@AllArgsConstructor
public class GiftController {

    private final GiftService giftService;
    private final GiftContributionService giftContributionService;

    @GetMapping("/getGifts/{idEvent}")
    public ResponseEntity<List<GiftDto>> getGiftsByEvent(@PathVariable Long idEvent) {
        List<GiftDto> giftDtos = giftService.getGiftsByEvent(idEvent);
        return new ResponseEntity<>(giftDtos, HttpStatus.OK);
    }

    @PutMapping("/giftIsPaid")
    public ResponseEntity<GiftDto> giftisPaid(@RequestBody GiftDto gift){
        GiftDto dto = giftService.updateisPaid(gift);
        return new ResponseEntity<GiftDto>(dto, HttpStatus.OK);
    }

    @PostMapping("/addGift")
    public ResponseEntity<GiftDto> addGift(@RequestBody GiftDto gift){
        GiftDto dto = giftService.addGift(gift);
        return new ResponseEntity<GiftDto>(dto,HttpStatus.OK);
    }

    @DeleteMapping("deleteGift/{idGift}")
    public ResponseEntity<Map<String, String>> deleteGift(@PathVariable Long idGift){
        giftContributionService.deleteGiftContributionFromIdGift(idGift);
        giftService.deleteGift(idGift);
        Map<String, String> reponse = new HashMap<>();
        reponse.put("token", "Gift supprimé avec succès");
        return ResponseEntity.ok(reponse);
    }
}