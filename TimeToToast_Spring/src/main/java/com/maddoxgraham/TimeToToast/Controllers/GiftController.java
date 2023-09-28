package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.GiftDto;
import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Services.GiftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gift")
@AllArgsConstructor
public class GiftController {

    private final GiftService giftService;

    @GetMapping("/getGifts/{idEvent}")
    public ResponseEntity<List<GiftDto>> getGiftsByEvent(@PathVariable Long idEvent) {
        List<GiftDto> giftDtos = giftService.getGiftsByEvent(idEvent);
        return new ResponseEntity<>(giftDtos, HttpStatus.OK);
    }


//
//    @GetMapping("/all")
//    public ResponseEntity<List<Gift>> getAllGift() {
//        List<Gift> gifts = giftService.findAllGifts();
//        return new ResponseEntity<>(gifts, HttpStatus.OK);
//    }
//
//    @GetMapping("/find/{idGift}")
//    public ResponseEntity<Gift> getGiftById(@PathVariable("idGift") Long idGift) {
//        Gift gift = giftService.findGiftByIdGift(idGift);
//        return new ResponseEntity<>(gift, HttpStatus.OK);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Gift> addGift(@RequestBody Gift gift){
//        Gift newGift = giftService.addGift(gift);
//        return new ResponseEntity<>(newGift, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Gift> updateGift(@RequestBody Gift gift){
//        Gift updateGift = giftService.updateGift(gift);
//        return new ResponseEntity<>(updateGift, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{idGift}")
//    public ResponseEntity<?> deleteGift(@PathVariable("idGift") Long idGift){
//        giftService.deleteGift(idGift);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}