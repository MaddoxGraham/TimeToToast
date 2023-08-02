package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    void deleteGiftByIdGift(Long idGift);
    Optional<Gift> findGiftByIdGift(Long idGift);
}
