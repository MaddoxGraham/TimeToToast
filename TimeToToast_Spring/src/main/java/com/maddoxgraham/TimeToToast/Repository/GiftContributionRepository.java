package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Gift;
import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiftContributionRepository extends JpaRepository<GiftContribution, Long> {
    void deleteGiftContributionByIdGiftContribution(Long idGiftContribution);

    void deleteByGift(Gift idGift);
    Optional<GiftContribution> findGiftContributionByIdGiftContribution(Long idGiftContribution);
    List<GiftContribution> findByGiftIdGift(Long idGift);

}
