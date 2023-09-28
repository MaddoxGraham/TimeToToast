package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    void deleteGiftByIdGift(Long idGift);
    Optional<Gift> findGiftByIdGift(Long idGift);
    List<Gift> findByEventIdEvent(Long idEvent);

}
